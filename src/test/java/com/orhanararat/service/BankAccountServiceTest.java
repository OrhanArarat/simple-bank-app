package com.orhanararat.service;

import com.orhanararat.dto.TransactionHistoryResponseDto;
import com.orhanararat.dto.request.CreateBankAccountRequestDto;
import com.orhanararat.dto.request.CreditRequestDto;
import com.orhanararat.dto.response.BankAccountHistoryResponseDto;
import com.orhanararat.dto.response.CreateBankAccountResponseDto;
import com.orhanararat.dto.response.CreditResponseDto;
import com.orhanararat.mapper.BankAccountMapper;
import com.orhanararat.model.BankAccount;
import com.orhanararat.model.DepositTransaction;
import com.orhanararat.model.Transaction;
import com.orhanararat.repository.bankaccount.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceTest {

    @InjectMocks
    private BankAccountService bankAccountService;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private BankAccountMapper bankAccountMapper;

    @Mock
    private TransactionService transactionService;

    @Test
    void credit() {
        CreditRequestDto requestDto = new CreditRequestDto(BigDecimal.TEN, "1234");
        CreditResponseDto responseDto = new CreditResponseDto("9876");

        Transaction transaction1 = new DepositTransaction(BigDecimal.TEN);

        List<Transaction> transactions = List.of(transaction1);

        BankAccount bankAccount = BankAccount.builder()
                .accountNumber("1234")
                .balance(BigDecimal.ZERO)
                .owner("Orhan Ararat")
                .previousBalance(BigDecimal.ZERO)
                .transactions(transactions)
                .build();


        when(bankAccountRepository.findBankAccountByAccountNumber("1234")).thenReturn(bankAccount);
        when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(bankAccount);
        when(transactionService.save(any(Transaction.class))).thenReturn(transaction1);
        CreditResponseDto creditResponseDto = bankAccountService.credit(requestDto);

        assertThat(creditResponseDto.getApprovalCode()).isEqualTo(transaction1.getApprovalCode());
    }

    @Test
    void createBankAccount() {
        CreateBankAccountRequestDto requestDto = new CreateBankAccountRequestDto("Orhan Ararat");
        BankAccount bankAccount = BankAccount.builder()
                .accountNumber("1234")
                .owner("Orhan Ararat")
                .build();

        when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(bankAccount);

        CreateBankAccountResponseDto createdBankAccount = bankAccountService.createBankAccount(requestDto);

        assertThat(createdBankAccount.getAccountNumber()).isEqualTo(bankAccount.getAccountNumber());
    }

    @Test
    void getHistory() {
        Transaction transaction1 = new DepositTransaction(BigDecimal.TEN);
        Transaction transaction2 = new DepositTransaction(BigDecimal.TEN);
        Transaction transaction3 = new DepositTransaction(BigDecimal.TEN);
        List<Transaction> transactions = List.of(transaction1, transaction2, transaction3);
        BankAccount bankAccount = BankAccount.builder()
                .accountNumber("1234")
                .balance(BigDecimal.ZERO)
                .owner("Orhan Ararat")
                .previousBalance(BigDecimal.ZERO)
                .transactions(transactions)
                .build();

        TransactionHistoryResponseDto transactionHistoryResponseDto1 = TransactionHistoryResponseDto.builder()
                .amount(BigDecimal.TEN)
                .approvalCode("1234")
                .type("DepositTransaction")
                .build();

        TransactionHistoryResponseDto transactionHistoryResponseDto2 = TransactionHistoryResponseDto.builder()
                .amount(BigDecimal.TEN)
                .approvalCode("1234")
                .type("DepositTransaction")
                .build();
        List<TransactionHistoryResponseDto> transactionHistoryResponseDtoList =
                List.of(transactionHistoryResponseDto1, transactionHistoryResponseDto2);


        BankAccountHistoryResponseDto bankAccountHistoryResponseDto = BankAccountHistoryResponseDto.builder()
                .accountNumber("1234")
                .transactions(transactionHistoryResponseDtoList)
                .balance(BigDecimal.TEN)
                .build();

        when(bankAccountRepository.findByAccountNumber("1234")).thenReturn(bankAccount);
        when(transactionService.getTransaction(bankAccount)).thenReturn(transactionHistoryResponseDtoList);
        when(bankAccountMapper.bankAccountToBankAccountHistoryDto(bankAccount)).thenReturn(bankAccountHistoryResponseDto);

        BankAccountHistoryResponseDto history = bankAccountService.getHistory("1234");

        assertThat(history.getBalance()).isEqualTo(BigDecimal.valueOf(10L));
    }

    @Test
    void phoneBillPayment() {
    }
}
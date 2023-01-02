package com.orhanararat.service;

import com.orhanararat.dto.TransactionHistoryResponseDto;
import com.orhanararat.dto.request.CreateBankAccountRequestDto;
import com.orhanararat.dto.request.CreditRequestDto;
import com.orhanararat.dto.request.DebitRequestDto;
import com.orhanararat.dto.request.PhoneBillPaymentRequestDto;
import com.orhanararat.dto.response.BankAccountHistoryResponseDto;
import com.orhanararat.dto.response.CreateBankAccountResponseDto;
import com.orhanararat.dto.response.CreditResponseDto;
import com.orhanararat.dto.response.DebitResponseDto;
import com.orhanararat.dto.response.PhoneBillResponseDto;
import com.orhanararat.mapper.BankAccountMapper;
import com.orhanararat.model.BankAccount;
import com.orhanararat.model.PhoneBillPaymentTransaction;
import com.orhanararat.model.Transaction;
import com.orhanararat.repository.bankaccount.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final TransactionService transactionService;

    private final BankAccountMapper bankAccountMapper;

    @Transactional
    public CreditResponseDto credit(CreditRequestDto requestDto) {
        BankAccount foundedAccount = bankAccountRepository.findBankAccountByAccountNumber(requestDto.getAccountNumber());
        Transaction creditTransaction = foundedAccount.credit(requestDto.getCreditAmount());
        BankAccount savedBankAccount = bankAccountRepository.save(foundedAccount);
        creditTransaction.setBankAccount(savedBankAccount);
        return CreditResponseDto.builder()
                .approvalCode(transactionService.save(creditTransaction).getApprovalCode())
                .build();
    }

    @Transactional
    public DebitResponseDto debit(DebitRequestDto requestDto) {
        BankAccount foundedAccount = bankAccountRepository.findBankAccountByAccountNumber(requestDto.getAccountNumber());
        Transaction debitTransaction = foundedAccount.debit(requestDto.getDebitAmount());
        BankAccount savedBankAccount = bankAccountRepository.save(foundedAccount);
        debitTransaction.setBankAccount(savedBankAccount);

        return DebitResponseDto.builder()
                .approvalCode(transactionService.save(debitTransaction).getApprovalCode())
                .build();
    }

    @Transactional
    public CreateBankAccountResponseDto createBankAccount(CreateBankAccountRequestDto requestDto) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setOwner(requestDto.getFullName());

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        return CreateBankAccountResponseDto.builder()
                .accountNumber(savedBankAccount.getAccountNumber())
                .build();
    }

    @Transactional
    public BankAccountHistoryResponseDto getHistory(String accountNumber) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber);
        List<TransactionHistoryResponseDto> transactionByAccountId = transactionService.getTransaction(bankAccount);
        BankAccountHistoryResponseDto responseDtoToMapper = bankAccountMapper.bankAccountToBankAccountHistoryDto(bankAccount);
        responseDtoToMapper.setTransactions(transactionByAccountId);
        return responseDtoToMapper;
    }

    @Transactional
    public PhoneBillResponseDto phoneBillPayment(PhoneBillPaymentRequestDto requestDto) {
        BankAccount foundedAccount = bankAccountRepository.findBankAccountByAccountNumber(requestDto.getAccountNumber());
        PhoneBillPaymentTransaction phoneBillPaymentTransaction = new PhoneBillPaymentTransaction(requestDto.getPayee(),
                requestDto.getSubscriberNumber(), requestDto.getAmount());
        foundedAccount.post(phoneBillPaymentTransaction);
        BankAccount savedBankAccount = bankAccountRepository.save(foundedAccount);
        phoneBillPaymentTransaction.setBankAccount(savedBankAccount);

        return PhoneBillResponseDto.builder()
                .approvalCode(transactionService.save(phoneBillPaymentTransaction).getApprovalCode())
                .build();
    }
}

package com.orhanararat.controller;

import com.orhanararat.dto.request.CreateBankAccountRequestDto;
import com.orhanararat.dto.request.CreditRequestDto;
import com.orhanararat.dto.request.DebitRequestDto;
import com.orhanararat.dto.request.PhoneBillPaymentRequestDto;
import com.orhanararat.dto.response.BankAccountHistoryResponseDto;
import com.orhanararat.dto.response.CreateBankAccountResponseDto;
import com.orhanararat.dto.response.CreditResponseDto;
import com.orhanararat.dto.response.DebitResponseDto;
import com.orhanararat.dto.response.PhoneBillResponseDto;
import com.orhanararat.service.BankAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankAccountControllerTest {

    @InjectMocks
    private BankAccountController bankAccountController;

    @Mock
    private BankAccountService bankAccountService;

    @Test
    void testCreateBankAccount() {
        CreateBankAccountRequestDto requestDto = new CreateBankAccountRequestDto("Orhan Ararat");
        CreateBankAccountResponseDto responseDto = new CreateBankAccountResponseDto("12344");

        when(bankAccountService.createBankAccount(requestDto)).thenReturn(responseDto);

        ResponseEntity<CreateBankAccountResponseDto> bankAccount = bankAccountController.createBankAccount(requestDto);

        assertThat(bankAccount.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(bankAccount.getBody()).getAccountNumber()).isEqualTo("12344");
    }

    @Test
    void testCredit() {
        CreditRequestDto requestDto = new CreditRequestDto(BigDecimal.valueOf(150L), "1234");
        CreditResponseDto responseDto = new CreditResponseDto("9876");

        when(bankAccountService.credit(requestDto)).thenReturn(responseDto);

        ResponseEntity<CreditResponseDto> credit = bankAccountController.credit(requestDto);

        assertThat(credit.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(credit.getBody()).getApprovalCode()).isEqualTo("9876");
    }

    @Test
    void testDebit() {
        DebitRequestDto requestDto = new DebitRequestDto(BigDecimal.valueOf(100L), "1234");
        DebitResponseDto responseDto = new DebitResponseDto("9876");

        when(bankAccountService.debit(requestDto)).thenReturn(responseDto);

        ResponseEntity<DebitResponseDto> debit = bankAccountController.debit(requestDto);

        assertThat(debit.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(debit.getBody()).getApprovalCode()).isEqualTo("9876");
    }

    @Test
    void testGetAccountHistory() {
        BankAccountHistoryResponseDto responseDto = BankAccountHistoryResponseDto.builder()
                .accountNumber("1234")
                .balance(BigDecimal.valueOf(100L))
                .build();

        when(bankAccountService.getHistory("1234")).thenReturn(responseDto);

        ResponseEntity<BankAccountHistoryResponseDto> accountHistory = bankAccountController.getAccountHistory("1234");

        assertThat(accountHistory.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(accountHistory.getBody()).getBalance()).isEqualTo(responseDto.getBalance());
        assertThat(Objects.requireNonNull(accountHistory.getBody().getAccountNumber())).isEqualTo(responseDto.getAccountNumber());
    }

    @Test
    void testPhoneBillPayment() {
        PhoneBillPaymentRequestDto requestDto = PhoneBillPaymentRequestDto.builder()
                .payee("Vodafone")
                .amount(BigDecimal.valueOf(55.5))
                .subscriberNumber("5555555555")
                .accountNumber("1234")
                .build();

        PhoneBillResponseDto responseDto = new PhoneBillResponseDto("9876");

        when(bankAccountService.phoneBillPayment(requestDto)).thenReturn(responseDto);

        ResponseEntity<PhoneBillResponseDto> phoneBillResponseDto = bankAccountController.phoneBillPayment(requestDto);

        assertThat(phoneBillResponseDto.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(phoneBillResponseDto.getBody()).getApprovalCode()).isEqualTo(responseDto.getApprovalCode());
    }
}
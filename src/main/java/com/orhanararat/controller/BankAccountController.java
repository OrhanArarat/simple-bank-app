package com.orhanararat.controller;

import com.orhanararat.constant.ApiConstant;
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
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.BANK_ACCOUNT)
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<CreateBankAccountResponseDto> createBankAccount(@RequestBody CreateBankAccountRequestDto requestDto) {
        return ResponseEntity.ok(bankAccountService.createBankAccount(requestDto));
    }

    @PostMapping(ApiConstant.CREDIT)
    public ResponseEntity<CreditResponseDto> credit(@RequestBody CreditRequestDto requestDto) {
        return ResponseEntity.ok(bankAccountService.credit(requestDto));
    }

    @PostMapping(ApiConstant.DEBIT)
    public ResponseEntity<DebitResponseDto> debit(@RequestBody DebitRequestDto requestDto) {
        return ResponseEntity.ok(bankAccountService.debit(requestDto));
    }

    @GetMapping(ApiConstant.HISTORY_ACCOUNT_NUMBER)
    public ResponseEntity<BankAccountHistoryResponseDto> getAccountHistory(@PathVariable("accountNumber") String accountNumber) {
        return ResponseEntity.ok(bankAccountService.getHistory(accountNumber));
    }

    @PostMapping(ApiConstant.PHONE_BILL_PAYMENT)
    public ResponseEntity<PhoneBillResponseDto> phoneBillPayment(@RequestBody PhoneBillPaymentRequestDto requestDto) {
        return ResponseEntity.ok(bankAccountService.phoneBillPayment(requestDto));
    }
}

package com.orhanararat.repository.bankaccount;

import com.orhanararat.dto.response.BankAccountHistoryResponseDto;
import com.orhanararat.model.BankAccount;

public interface BankAccountDslRepository {

    BankAccount findBankAccountByAccountNumber(String accountNumber);

    BankAccountHistoryResponseDto getBankAccountHistory(String accountNumber);
}

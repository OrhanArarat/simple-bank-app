package com.orhanararat.repository.transaction;

import com.orhanararat.model.Transaction;

import java.util.List;

public interface TransactionDslRepository {
    List<Transaction> getAllTransactionByAccountNumber(Long accountId);
}

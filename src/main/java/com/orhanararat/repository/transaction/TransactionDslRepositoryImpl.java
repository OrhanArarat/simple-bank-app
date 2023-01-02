package com.orhanararat.repository.transaction;

import com.orhanararat.model.Transaction;
import com.orhanararat.repository.BaseRepositoryImpl;

import javax.persistence.EntityManager;
import java.util.List;

public class TransactionDslRepositoryImpl extends BaseRepositoryImpl<Transaction, Long> implements TransactionDslRepository {
    public TransactionDslRepositoryImpl(EntityManager em) {
        super(Transaction.class, em);
    }

    @Override
    public List<Transaction> getAllTransactionByAccountNumber(Long accountId) {
        return queryFactory
                .select(transaction)
                .from(transaction)
                .where(transaction.bankAccount.id.eq(accountId))
                .fetch();
    }
}

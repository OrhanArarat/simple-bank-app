package com.orhanararat.repository.bankaccount;

import com.orhanararat.dto.response.BankAccountHistoryResponseDto;
import com.orhanararat.model.BankAccount;
import com.orhanararat.repository.BaseRepositoryImpl;
import com.querydsl.core.types.Projections;

import javax.persistence.EntityManager;

public class BankAccountDslRepositoryImpl extends BaseRepositoryImpl<BankAccount, Long> implements BankAccountDslRepository {
    public BankAccountDslRepositoryImpl(EntityManager em) {
        super(BankAccount.class, em);
    }

    @Override
    public BankAccount findBankAccountByAccountNumber(String accountNumber) {
        return queryFactory
                .select(bankAccount)
                .from(bankAccount)
                .where(bankAccount.accountNumber.eq(accountNumber))
                .fetchFirst();
    }

    @Override
    public BankAccountHistoryResponseDto getBankAccountHistory(String accountNumber) {
        return queryFactory
                .select(Projections.constructor(BankAccountHistoryResponseDto.class, bankAccount.accountNumber,
                        bankAccount.id, bankAccount.owner, bankAccount.balance, bankAccount.createDate))
                .from(bankAccount)
                .where(bankAccount.accountNumber.eq(accountNumber))
                .fetchFirst();
    }
}

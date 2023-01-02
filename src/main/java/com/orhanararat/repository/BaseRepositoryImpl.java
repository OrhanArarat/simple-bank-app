package com.orhanararat.repository;

import com.orhanararat.model.QBankAccount;
import com.orhanararat.model.QTransaction;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

public abstract class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> {
    protected static final QBankAccount bankAccount = QBankAccount.bankAccount;
    protected static final QTransaction transaction = QTransaction.transaction;
    private final EntityManager em;
    protected final JPAQueryFactory queryFactory;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public void clear() {
        em.clear();
    }

    public void detach(T entity) {
        em.detach(entity);
    }
}

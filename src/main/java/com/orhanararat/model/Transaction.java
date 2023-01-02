package com.orhanararat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
//@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSACTION")
@SequenceGenerator(name = "SEQ", sequenceName = "seq_transactions", allocationSize = 1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Transaction extends BaseEntity {
    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "approval_code")
    private String approvalCode = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private BankAccount bankAccount;

    public Transaction(BigDecimal amount) {
        this.amount = amount;
    }

    public abstract BigDecimal actualAmount();
}

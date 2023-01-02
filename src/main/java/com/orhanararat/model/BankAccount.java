package com.orhanararat.model;

import com.orhanararat.exception.OutOfBalanceException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "BANK_ACCOUNT")
@SequenceGenerator(name = "SEQ", sequenceName = "seq_bank_account", allocationSize = 1)
public class BankAccount extends BaseEntity {
    @Column(name = "owner")
    private String owner;

    @Column(name = "account_number")
    private String accountNumber = UUID.randomUUID().toString();

    @Column(name = "balance", nullable = false, columnDefinition = "numeric(19,2) default 0")
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "previous_balance", nullable = false, columnDefinition = "numeric(19,2) default 0")
    private BigDecimal previousBalance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "bankAccount")
    @Fetch(FetchMode.SUBSELECT)
    private List<Transaction> transactions;

    public void post(Transaction transaction) {
        BigDecimal value = this.getBalance().add(transaction.actualAmount());
        this.setPreviousBalance(this.getBalance());
        this.setBalance(value);
    }

    public Transaction credit(BigDecimal creditValue) {
        Transaction depositTransaction = new DepositTransaction(creditValue);
        this.post(depositTransaction);
        return depositTransaction;
    }

    public Transaction debit(BigDecimal debitValue) {
        BigDecimal value = this.getBalance().subtract(debitValue);
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new OutOfBalanceException();
        }
        Transaction withdrawalTransaction = new WithdrawalTransaction(debitValue);
        this.post(withdrawalTransaction);
        return withdrawalTransaction;
    }

}

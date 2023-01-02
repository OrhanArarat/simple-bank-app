package com.orhanararat.model;

import com.orhanararat.enums.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue(TransactionType.Values.DEPOSIT)
public class DepositTransaction extends Transaction {
    public DepositTransaction(BigDecimal creditValue) {
        super(creditValue);
    }

    @Override
    public BigDecimal actualAmount() {
        return getAmount();
    }
}

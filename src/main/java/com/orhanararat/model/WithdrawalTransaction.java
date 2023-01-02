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
@DiscriminatorValue(TransactionType.Values.WITHDRAWAL)
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(BigDecimal amount) {
        super(amount);
    }

    @Override
    public BigDecimal actualAmount() {
        return getAmount().negate();
    }
}

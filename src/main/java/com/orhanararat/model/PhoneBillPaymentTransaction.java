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
@DiscriminatorValue(TransactionType.Values.PHONE_BILL_PAYMENT)
public class PhoneBillPaymentTransaction extends Transaction {

    private String payee;
    private String subscriberNumber;

    public PhoneBillPaymentTransaction(String payee, String subscriberNumber, BigDecimal amount) {
        this.payee = payee;
        this.subscriberNumber = subscriberNumber;
        this.setAmount(amount);
    }

    @Override
    public BigDecimal actualAmount() {
        return getAmount().negate();
    }
}

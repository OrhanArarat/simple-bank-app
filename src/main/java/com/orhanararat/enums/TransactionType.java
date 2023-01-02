package com.orhanararat.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum TransactionType {
    DEPOSIT(Values.DEPOSIT), WITHDRAWAL(Values.WITHDRAWAL), BILL_PAYMENT(Values.BILL_PAYMENT),
    PHONE_BILL_PAYMENT(Values.PHONE_BILL_PAYMENT);
    private String value;

    public static class Values {
        public static final String DEPOSIT = "DEPOSIT";
        public static final String WITHDRAWAL = "WITHDRAWAL";
        public static final String BILL_PAYMENT = "BILL_PAYMENT";
        public static final String PHONE_BILL_PAYMENT = "PHONE_BILL_PAYMENT";
    }
}

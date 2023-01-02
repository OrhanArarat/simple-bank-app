package com.orhanararat.exception;

public class OutOfBalanceException extends RuntimeException {
    public OutOfBalanceException() {
        super("OutOfBalance: Var olan bakiyenizden fazlasini cekemezsiniz!!!");
    }
}

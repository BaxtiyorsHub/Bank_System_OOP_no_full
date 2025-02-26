package org.example.exp;

public class TransactionNotFoundExp extends RuntimeException {
    public TransactionNotFoundExp(String message) {
        super(message);
    }
}

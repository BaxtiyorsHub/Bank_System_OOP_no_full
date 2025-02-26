package org.example.exp;

public class CardNotFoundExp extends RuntimeException {
    public CardNotFoundExp(String message) {
        super(message);
    }
}

package com.exalt_it.bankaccount.domain.model;

public enum TransactionType {
    DEPOSIT("DEPOSIT"),
    WITHDRAWAL("WITHDRAWAL");

    private final String code;

    TransactionType(String code) {
        this.code = code;
    }
}

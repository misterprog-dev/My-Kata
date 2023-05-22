package com.exalt_it.bankaccount.application.data.response;

import java.util.List;

public class AccountCreatedResponse {
    private String email;
    private List<TransactionResponse> transactions;

    public AccountCreatedResponse() {
    }

    public AccountCreatedResponse(String email, List<TransactionResponse> transactions) {
        this.email = email;
        this.transactions = transactions;
    }

    public String getEmail() {
        return email;
    }

    public List<TransactionResponse> getTransactions() {
        return transactions;
    }
}

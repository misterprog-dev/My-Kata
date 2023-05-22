package com.exalt_it.bankaccount.application.data.response;

import java.util.List;

public class DepositResponse {
    private List<TransactionResponse> transactions;

    public DepositResponse() {
    }

    public DepositResponse(List<TransactionResponse> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionResponse> getTransactions() {
        return transactions;
    }
}

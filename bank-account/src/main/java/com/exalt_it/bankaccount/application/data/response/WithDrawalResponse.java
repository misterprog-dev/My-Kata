package com.exalt_it.bankaccount.application.data.response;

import java.util.List;

public class WithDrawalResponse {
    private List<TransactionResponse> transactions;

    public WithDrawalResponse() {
    }

    public WithDrawalResponse(List<TransactionResponse> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionResponse> getTransactions() {
        return transactions;
    }
}

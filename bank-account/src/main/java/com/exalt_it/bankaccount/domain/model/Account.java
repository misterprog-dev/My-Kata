package com.exalt_it.bankaccount.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.exalt_it.bankaccount.domain.model.Transaction.moneyForDeposit;
import static com.exalt_it.bankaccount.domain.model.Transaction.moneyForWithDrawal;
import static com.exalt_it.bankaccount.domain.model.TransactionType.DEPOSIT;
import static com.exalt_it.bankaccount.domain.model.TransactionType.WITHDRAWAL;
import static java.math.BigDecimal.ZERO;

public class Account implements TransactionForDeposit, TransactionForWithDrawal, StatementPrinting {
    private final String email;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String email) {
        this.email = email;
    }

    @Override
    public void recordDeposit(BigDecimal money, LocalDateTime date) {
        transactions.add(moneyForDeposit(money, DEPOSIT, date, getCurrentBalance()));
    }

    @Override
    public void recordWithDrawal(BigDecimal money, LocalDateTime date) {
        transactions.add(moneyForWithDrawal(money, WITHDRAWAL, date, getCurrentBalance()));
    }

    @Override
    public String printStatement() {
        return new Statement(transactions).print();
    }

    public BigDecimal getCurrentBalance() {
        return transactions.stream()
                .map(Transaction::getAmountValue)
                .reduce(ZERO, BigDecimal::add);
    }

    public String getEmail() {
        return email;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        final Account other = (Account) object;
        return email.equals(other.email);
    }
}

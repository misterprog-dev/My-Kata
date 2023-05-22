package com.exalt_it.bankaccount.application.mapper;

import com.exalt_it.bankaccount.domain.model.Account;
import com.exalt_it.bankaccount.domain.model.Operation;
import com.exalt_it.bankaccount.application.data.request.AccountCreateRequest;
import com.exalt_it.bankaccount.application.data.request.DepositRequest;
import com.exalt_it.bankaccount.application.data.request.WithDrawalRequest;
import com.exalt_it.bankaccount.application.data.response.AccountCreatedResponse;
import com.exalt_it.bankaccount.application.data.response.DepositResponse;
import com.exalt_it.bankaccount.application.data.response.TransactionResponse;
import com.exalt_it.bankaccount.application.data.response.WithDrawalResponse;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.LocalDateTime.now;

@Service
public class AccountRestMapper {
    public Account toAccount(AccountCreateRequest request) {
        return new Account(request.getEmail());
    }

    public AccountCreatedResponse toAccountCreateResponse(Account account) {
        List<TransactionResponse> transactions = getTransactionsResponses(account);
        return new AccountCreatedResponse(account.getEmail(), transactions);
    }

    public Operation toOperation(DepositRequest request) {
        return Operation.of(request.getEmail(), request.getMoney(), now());
    }

    public DepositResponse toAccountDeposit(Account account) {
        return new DepositResponse(getTransactionsResponses(account));
    }

    public Operation toOperation(WithDrawalRequest request) {
        return Operation.of(request.getEmail(), request.getMoney(), now());
    }

    public WithDrawalResponse toAccountWithDrawal(Account account) {
        return new WithDrawalResponse(getTransactionsResponses(account));
    }

    private List<TransactionResponse> getTransactionsResponses(Account account) {
        return account.getTransactions().stream()
                .map(t -> new TransactionResponse(t.money(), t.type(), t.date()))
                .toList();
    }
}

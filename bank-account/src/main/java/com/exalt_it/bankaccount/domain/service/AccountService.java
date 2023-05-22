package com.exalt_it.bankaccount.domain.service;

import com.exalt_it.bankaccount.domain.event.*;
import com.exalt_it.bankaccount.domain.ports.input.*;
import com.exalt_it.bankaccount.domain.ports.output.AccountEventPublisher;
import com.exalt_it.bankaccount.domain.ports.output.AccountOutputPort;
import com.exalt_it.bankaccount.domain.model.Account;
import com.exalt_it.bankaccount.domain.model.Operation;

public class AccountService implements CreateAccountUseCase,
        MakeDepositUseCase,
        MakeWithDrawalUseCase,
        GetStatementUseCase,
        GetCurrentBalanceUseCase {
    private final AccountOutputPort accountOutputPort;
    private final AccountEventPublisher accountEventPublisher;

    public AccountService(AccountOutputPort accountOutputPort, AccountEventPublisher accountEventPublisher) {
        this.accountOutputPort = accountOutputPort;
        this.accountEventPublisher = accountEventPublisher;
    }

    @Override
    public Account create(String email) {
        Account account = accountOutputPort.createAccount(email);
        accountEventPublisher.publishAccountCreateEvent(new AccountCreatedEvent(email));
        return account;
    }

    @Override
    public Account makeDeposit(Operation operation) {
        Account account = accountOutputPort.makeDeposit(operation);
        accountEventPublisher.publishDepositEvent(new DepositEvent(operation.email(), operation.money()));
        return account;
    }

    @Override
    public Account makeWithDrawal(Operation operation) {
        Account account = accountOutputPort.makeWithDrawal(operation);
        accountEventPublisher.publishWithDrawalEvent(new WithDrawalEvent(operation.email(), operation.money()));
        return account;
    }

    @Override
    public String printStatement(String email) {
        String result = accountOutputPort.printStatement(email);
        accountEventPublisher.publishPrintStatement(new PrintStatementEvent(email));
        return result;
    }

    @Override
    public String displayCurrentBalance(String email) {
        String result = accountOutputPort.displayCurrentBalance(email);
        accountEventPublisher.publishCurrentBalance(new CurrentBalanceEvent(email));
        return result;
    }
}

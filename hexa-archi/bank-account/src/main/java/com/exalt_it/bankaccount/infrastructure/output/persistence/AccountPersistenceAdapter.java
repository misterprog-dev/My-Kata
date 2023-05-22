package com.exalt_it.bankaccount.infrastructure.output.persistence;

import com.exalt_it.bankaccount.domain.ports.output.AccountOutputPort;
import com.exalt_it.bankaccount.domain.model.Account;
import com.exalt_it.bankaccount.domain.model.Operation;
import com.exalt_it.bankaccount.infrastructure.output.exception.AccountAlreadyExistsException;
import com.exalt_it.bankaccount.infrastructure.output.exception.AccountNotFoundException;
import com.exalt_it.bankaccount.infrastructure.output.persistence.mapper.AccountPersistenceMapper;
import com.exalt_it.bankaccount.infrastructure.output.persistence.entity.AccountEntity;
import com.exalt_it.bankaccount.infrastructure.output.persistence.entity.TransactionEntity;
import com.exalt_it.bankaccount.infrastructure.output.persistence.repository.AccountRepository;

import static com.exalt_it.bankaccount.domain.model.TransactionType.DEPOSIT;
import static com.exalt_it.bankaccount.domain.model.TransactionType.WITHDRAWAL;


public class AccountPersistenceAdapter implements AccountOutputPort {
    private final AccountRepository accountRepository;
    private final AccountPersistenceMapper accountPersistenceMapper;

    public AccountPersistenceAdapter(AccountRepository accountRepository, AccountPersistenceMapper accountPersistenceMapper) {
        this.accountRepository = accountRepository;
        this.accountPersistenceMapper = accountPersistenceMapper;
    }


    @Override
    public Account createAccount(String email) {
        if (accountRepository.findByEmail(email).isPresent()) {
            throw new AccountAlreadyExistsException("Error : this email already exists !");
        }

        AccountEntity accountToSave = accountPersistenceMapper.accountToAccountEntity(email);
        accountToSave = accountRepository.save(accountToSave);
        return accountPersistenceMapper.accountEntityToAccount(accountToSave);
    }

    @Override
    public Account makeDeposit(Operation operation) {
        AccountEntity accountEntity = getAccountFor(operation.email());
        accountEntity = saveDepositOperation(operation, accountEntity);
        return accountPersistenceMapper.accountEntityToAccount(accountEntity);
    }

    @Override
    public Account makeWithDrawal(Operation operation) {
        AccountEntity accountEntity = getAccountFor(operation.email());
        accountEntity = saveWithDrawalOperation(operation, accountEntity);
        return accountPersistenceMapper.accountEntityToAccount(accountEntity);
    }

    @Override
    public String printStatement(String email) {
        AccountEntity accountEntity = getAccountFor(email);
        Account account = accountPersistenceMapper.accountEntityToAccount(accountEntity);
        return account.printStatement();
    }

    @Override
    public String displayCurrentBalance(String email) {
        AccountEntity accountEntity = getAccountFor(email);
        Account account = accountPersistenceMapper.accountEntityToAccount(accountEntity);
        return account.getCurrentBalance().toString();
    }

    private AccountEntity getAccountFor(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException("Error : this account doesn't exist !"));
    }

    private AccountEntity saveDepositOperation(Operation operation, AccountEntity accountEntity) {
        Account account = accountPersistenceMapper.accountEntityToAccount(accountEntity);
        accountEntity.addTransaction(new TransactionEntity(operation.money(),
                DEPOSIT,
                operation.date(),
                account.getCurrentBalance().add(operation.money())));
        accountEntity = accountRepository.save(accountEntity);
        return accountEntity;
    }

    private AccountEntity saveWithDrawalOperation(Operation operation, AccountEntity accountEntity) {
        Account account = accountPersistenceMapper.accountEntityToAccount(accountEntity);
        accountEntity.addTransaction(new TransactionEntity(operation.money(),
                WITHDRAWAL,
                operation.date(),
                account.getCurrentBalance().subtract(operation.money())));
        accountEntity = accountRepository.save(accountEntity);
        return accountEntity;
    }
}

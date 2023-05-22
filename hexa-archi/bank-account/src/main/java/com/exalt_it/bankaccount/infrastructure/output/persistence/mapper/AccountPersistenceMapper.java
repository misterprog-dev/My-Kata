package com.exalt_it.bankaccount.infrastructure.output.persistence.mapper;

import com.exalt_it.bankaccount.domain.model.Account;
import com.exalt_it.bankaccount.domain.model.Transaction;
import com.exalt_it.bankaccount.infrastructure.output.persistence.entity.AccountEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountPersistenceMapper {
    public AccountEntity accountToAccountEntity(String email) {
        return new AccountEntity(email);
    }

    public Account accountEntityToAccount(AccountEntity accountEntity) {
        Account account = new Account(accountEntity.getEmail());
        List<Transaction> transactions = accountEntity.getTransactions()
                .stream()
                .map(t -> new Transaction(t.getMoney(), t.getType(), t.getDate(), t.getCurrentBalance()))
                .toList();
        account.setTransactions(transactions);
        return account;
    }
}

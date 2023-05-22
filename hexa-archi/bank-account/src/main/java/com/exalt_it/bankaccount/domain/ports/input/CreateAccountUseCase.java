package com.exalt_it.bankaccount.domain.ports.input;

import com.exalt_it.bankaccount.domain.model.Account;

public interface CreateAccountUseCase {
    Account create(String email);
}

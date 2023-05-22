package com.exalt_it.bankaccount.domain.ports.input;

import com.exalt_it.bankaccount.domain.model.Account;
import com.exalt_it.bankaccount.domain.model.Operation;

public interface MakeWithDrawalUseCase {
    Account makeWithDrawal(Operation operation);
}

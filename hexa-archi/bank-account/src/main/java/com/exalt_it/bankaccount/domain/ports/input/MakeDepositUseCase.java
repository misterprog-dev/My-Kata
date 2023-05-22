package com.exalt_it.bankaccount.domain.ports.input;

import com.exalt_it.bankaccount.domain.model.Account;
import com.exalt_it.bankaccount.domain.model.Operation;

public interface MakeDepositUseCase {
    Account makeDeposit(Operation operation);
}

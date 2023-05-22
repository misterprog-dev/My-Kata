package com.exalt_it.bankaccount.domain.ports.output;

import com.exalt_it.bankaccount.domain.model.Account;
import com.exalt_it.bankaccount.domain.model.Operation;

public interface AccountOutputPort {
    Account createAccount(String email);
    Account makeDeposit(Operation operation);
    Account makeWithDrawal(Operation operation);
    String printStatement(String email);
    String displayCurrentBalance(String email);
}

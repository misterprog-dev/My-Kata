package com.exalt_it.bankaccount.domain.ports.output;

import com.exalt_it.bankaccount.domain.event.*;

public interface AccountEventPublisher {
    void publishAccountCreateEvent(AccountCreatedEvent event);
    void publishDepositEvent(DepositEvent event);
    void publishWithDrawalEvent(WithDrawalEvent event);
    void publishCurrentBalance(CurrentBalanceEvent event);
    void publishPrintStatement(PrintStatementEvent event);
}

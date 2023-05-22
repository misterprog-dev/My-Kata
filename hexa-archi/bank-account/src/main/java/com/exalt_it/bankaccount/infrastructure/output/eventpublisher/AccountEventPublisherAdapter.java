package com.exalt_it.bankaccount.infrastructure.output.eventpublisher;

import com.exalt_it.bankaccount.domain.event.*;
import com.exalt_it.bankaccount.domain.ports.output.AccountEventPublisher;
import org.springframework.context.ApplicationEventPublisher;

public class AccountEventPublisherAdapter implements AccountEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public AccountEventPublisherAdapter(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publishAccountCreateEvent(AccountCreatedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void publishDepositEvent(DepositEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void publishWithDrawalEvent(WithDrawalEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void publishCurrentBalance(CurrentBalanceEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void publishPrintStatement(PrintStatementEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}

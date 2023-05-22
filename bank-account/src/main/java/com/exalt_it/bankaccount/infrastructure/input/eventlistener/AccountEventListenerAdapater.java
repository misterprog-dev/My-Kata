package com.exalt_it.bankaccount.infrastructure.input.eventlistener;

import com.exalt_it.bankaccount.domain.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class AccountEventListenerAdapater {
    private final Logger logger = Logger.getLogger(AccountEventListenerAdapater.class.getName());

    @EventListener
    public void handle(AccountCreatedEvent event) {
        logger.info("Account created with Email=" + event.getEmail() + " at " + event.getDate());
    }

    @EventListener
    public void handle(DepositEvent event) {
        logger.info("Deposit for Account=" + event.getEmail() + " with amount=" + event.getMoney() + " at " + event.getDate());
    }

    @EventListener
    public void handle(WithDrawalEvent event) {
        logger.info("WithDrawal for Account=" + event.getEmail() + " with amount=" + event.getMoney() + " at " + event.getDate());
    }

    @EventListener
    public void handle(CurrentBalanceEvent event) {
        logger.info("Getting current statement for Account=" + event.getEmail() + " at " + event.getDate());
    }

    @EventListener
    public void handle(PrintStatementEvent event) {
        logger.info("Printing statement for Account=" + event.getEmail() + " at " + event.getDate());
    }
}

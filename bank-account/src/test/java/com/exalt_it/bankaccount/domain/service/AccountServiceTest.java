package com.exalt_it.bankaccount.domain.service;

import com.exalt_it.bankaccount.domain.ports.output.AccountEventPublisher;
import com.exalt_it.bankaccount.domain.ports.output.AccountOutputPort;
import com.exalt_it.bankaccount.domain.model.Account;
import com.exalt_it.bankaccount.domain.model.Operation;
import com.exalt_it.bankaccount.domain.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.exalt_it.bankaccount.domain.model.TransactionType.DEPOSIT;
import static com.exalt_it.bankaccount.domain.model.TransactionType.WITHDRAWAL;
import static java.time.LocalDateTime.of;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountOutputPort accountOutputPort;

    @Mock
    private AccountEventPublisher accountEventPublisher;
    @InjectMocks
    private AccountService accountService;

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account("sd@kata.com");
    }

    @Test
    void should_create_account() {
        // given
        when(accountOutputPort.createAccount(account.getEmail())).thenReturn(account);

        // when
        accountService.create(account.getEmail());

        // then
        verify(accountOutputPort).createAccount(account.getEmail());
    }

    @Test
    void should_make_deposit() {
        // given
        LocalDateTime dateOfdeposit = of(2023, 5, 3, 10, 0, 0);
        account.setTransactions(List.of(new Transaction(new BigDecimal(200), DEPOSIT, dateOfdeposit, new BigDecimal(200))));
        Operation operation = new Operation(account.getEmail(), new BigDecimal(200), dateOfdeposit);
        when(accountOutputPort.makeDeposit(operation)).thenReturn(account);

        // when
        accountService.makeDeposit(operation);

        // then
        verify(accountOutputPort).makeDeposit(operation);
    }

    @Test
    void should_make_withdrawal() {
        // given
        LocalDateTime dateOfWithDrawal = of(2023, 5, 3, 10, 0, 0);
        account.setTransactions(List.of(new Transaction(new BigDecimal(-200), WITHDRAWAL, dateOfWithDrawal, new BigDecimal(-200))));
        Operation operation = new Operation(account.getEmail(), new BigDecimal(-200), dateOfWithDrawal);
        when(accountOutputPort.makeWithDrawal(operation)).thenReturn(account);

        // when
        accountService.makeWithDrawal(operation);

        // then
        verify(accountOutputPort).makeWithDrawal(operation);
    }

    @Test
    void should_print_statement() {
        // given
        account.setTransactions(List.of(new Transaction(new BigDecimal(-200), WITHDRAWAL, of(2023, 5, 3, 10, 0, 0), new BigDecimal(-200))));

        // when
        accountService.printStatement(account.getEmail());

        // then
        verify(accountOutputPort).printStatement(account.getEmail());
    }

    @Test
    void should_get_current_balance() {
        // given
        account.setTransactions(List.of(new Transaction(new BigDecimal(-200), WITHDRAWAL, of(2023, 5, 3, 10, 0, 0), new BigDecimal(-200))));

        // when
        accountService.displayCurrentBalance(account.getEmail());

        // then
        verify(accountOutputPort).displayCurrentBalance(account.getEmail());
    }
}

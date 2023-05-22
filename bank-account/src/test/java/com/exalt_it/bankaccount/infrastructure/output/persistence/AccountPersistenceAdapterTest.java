package com.exalt_it.bankaccount.infrastructure.output.persistence;

import com.exalt_it.bankaccount.domain.model.Account;
import com.exalt_it.bankaccount.domain.model.Operation;
import com.exalt_it.bankaccount.infrastructure.output.exception.AccountAlreadyExistsException;
import com.exalt_it.bankaccount.infrastructure.output.exception.AccountNotFoundException;
import com.exalt_it.bankaccount.infrastructure.output.persistence.entity.AccountEntity;
import com.exalt_it.bankaccount.infrastructure.output.persistence.mapper.AccountPersistenceMapper;
import com.exalt_it.bankaccount.infrastructure.output.persistence.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountPersistenceAdapterTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountPersistenceMapper accountPersistenceMapper;

    @InjectMocks
    private AccountPersistenceAdapter accountPersistenceAdapter;


    @Test
    void should_throw_when_create_account_with_already_existing() {
        // given
        Account account = new Account("sd@kata.fr");
        when(accountRepository.findByEmail(account.getEmail())).thenReturn(of(new AccountEntity()));

        // when
        Exception exception = assertThrows(AccountAlreadyExistsException.class, () -> accountPersistenceAdapter.createAccount(account.getEmail()));

        // then
        assertEquals("Error : this email already exists !", exception.getMessage());
        verify(accountRepository).findByEmail(account.getEmail());
        verify(accountRepository, never()).save(any());
    }

    @Test
    void should_create_account() {
        // given
        Account account = new Account("sd@kata.fr");
        AccountEntity accountEntity = new AccountEntity("sd@kata.fr");
        when(accountRepository.findByEmail(account.getEmail())).thenReturn(empty());
        when(accountPersistenceMapper.accountToAccountEntity(account.getEmail())).thenReturn(accountEntity);

        // when
        accountPersistenceAdapter.createAccount(account.getEmail());

        // then
        verify(accountRepository).findByEmail(account.getEmail());
        verify(accountPersistenceMapper).accountToAccountEntity(account.getEmail());
        verify(accountRepository, times(1)).save(any(AccountEntity.class));
    }

    @Test
    void should_throw_when_make_deposit_on_account_with_account_does_not_exist() {
        // given
        Account account = new Account("sd@kata.fr");
        when(accountRepository.findByEmail(account.getEmail())).thenReturn(empty());

        // when
        Exception exception = assertThrows(AccountNotFoundException.class, () -> accountPersistenceAdapter.makeDeposit(new Operation(account.getEmail(), new BigDecimal(100), now())));

        // then
        assertEquals("Error : this account doesn't exist !", exception.getMessage());
        verify(accountRepository).findByEmail(account.getEmail());
        verify(accountRepository, never()).save(any());
    }

    @Test
    void should_make_deposit_on_account() {
        // given
        Account account = new Account("sd@kata.fr");
        AccountEntity accountEntity = new AccountEntity("sd@kata.fr");
        when(accountRepository.findByEmail(account.getEmail())).thenReturn(of(accountEntity));
        when(accountPersistenceMapper.accountEntityToAccount(accountEntity)).thenReturn(account);
        LocalDateTime dateOfOperation = now();

        // when
        accountPersistenceAdapter.makeDeposit(new Operation(account.getEmail(), new BigDecimal(100), dateOfOperation));

        // then
        assertEquals(accountEntity.getTransactions().size(), 1);
        verify(accountRepository).findByEmail(account.getEmail());
        verify(accountPersistenceMapper).accountEntityToAccount(accountEntity);
        verify(accountRepository, times(1)).save(any(AccountEntity.class));
    }

    @Test
    void should_throw_when_make_withdrawal_on_account_with_account_does_not_exist() {
        // given
        Account account = new Account("sd@kata.fr");
        when(accountRepository.findByEmail(account.getEmail())).thenReturn(empty());

        // when
        Exception exception = assertThrows(AccountNotFoundException.class, () -> accountPersistenceAdapter.makeWithDrawal(new Operation(account.getEmail(), new BigDecimal(100), now())));

        // then
        assertEquals("Error : this account doesn't exist !", exception.getMessage());
        verify(accountRepository).findByEmail(account.getEmail());
        verify(accountRepository, never()).save(any());
    }

    @Test
    void should_make_withdrawal_on_account() {
        // given
        Account account = new Account("sd@kata.fr");
        AccountEntity accountEntity = new AccountEntity("sd@kata.fr");
        when(accountRepository.findByEmail(account.getEmail())).thenReturn(of(accountEntity));
        when(accountPersistenceMapper.accountEntityToAccount(accountEntity)).thenReturn(account);
        LocalDateTime dateOfOperation = now();

        // when
        accountPersistenceAdapter.makeWithDrawal(new Operation(account.getEmail(), new BigDecimal(100), dateOfOperation));

        // then
        assertEquals(accountEntity.getTransactions().size(), 1);
        verify(accountRepository).findByEmail(account.getEmail());
        verify(accountPersistenceMapper).accountEntityToAccount(accountEntity);
        verify(accountRepository, times(1)).save(any(AccountEntity.class));
    }

    @Test
    void should_throw_when_print_statement_for_account_does_not_exist() {
        // given
        Account account = new Account("sd@kata.fr");
        when(accountRepository.findByEmail(account.getEmail())).thenReturn(empty());

        // when
        Exception exception = assertThrows(AccountNotFoundException.class, () -> accountPersistenceAdapter.printStatement(account.getEmail()));

        // then
        assertEquals("Error : this account doesn't exist !", exception.getMessage());
        verify(accountRepository).findByEmail(account.getEmail());
    }

    @Test
    void should_print_statement_for_account() {
        // given
        Account account = new Account("sd@kata.fr");
        AccountEntity accountEntity = new AccountEntity("sd@kata.fr");
        when(accountRepository.findByEmail(account.getEmail())).thenReturn(of(accountEntity));
        when(accountPersistenceMapper.accountEntityToAccount(accountEntity)).thenReturn(account);

        // when
        String result = accountPersistenceAdapter.printStatement(account.getEmail());

        // then
        verify(accountRepository).findByEmail(account.getEmail());
        assertNotNull(result);
    }

    @Test
    void should_throw_when_display_current_balance_for_account_does_not_exist() {
        // given
        Account account = new Account("sd@kata.fr");
        when(accountRepository.findByEmail(account.getEmail())).thenReturn(empty());

        // when
        Exception exception = assertThrows(AccountNotFoundException.class, () -> accountPersistenceAdapter.displayCurrentBalance(account.getEmail()));

        // then
        assertEquals("Error : this account doesn't exist !", exception.getMessage());
        verify(accountRepository).findByEmail(account.getEmail());
    }

    @Test
    void should_display_current_balance_for_account() {
        // given
        Account account = new Account("sd@kata.fr");
        AccountEntity accountEntity = new AccountEntity("sd@kata.fr");
        when(accountRepository.findByEmail(account.getEmail())).thenReturn(of(accountEntity));
        when(accountPersistenceMapper.accountEntityToAccount(accountEntity)).thenReturn(account);

        // when
        String result = accountPersistenceAdapter.displayCurrentBalance(account.getEmail());

        // then
        verify(accountRepository).findByEmail(account.getEmail());
        assertNotNull(result);
    }
}

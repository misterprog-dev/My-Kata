package com.exalt_it.bankaccount.domain.model;

import com.exalt_it.bankaccount.domain.common.exception.MoneyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {
    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account("sd@kata.com");
    }

    @Test
    void should_throw_when_add_money_to_the_account_with_money_is_null() {
        // given
        LocalDateTime dateOfDeposit = of(2023, 5, 3, 10, 0, 0);

        // when
        Exception exception = assertThrows(MoneyException.class, () -> account.recordDeposit(null, dateOfDeposit));

        // then
        assertEquals("Error : Impossible de prendre en compte un montant NULL", exception.getMessage());
    }

    @Test
    void should_throw_when_add_money_to_the_account_with_money_is_negative() {
        // given
        LocalDateTime dateOfDeposit = of(2023, 5, 3, 10, 0, 0);

        // when
        Exception exception = assertThrows(MoneyException.class, () -> account.recordDeposit(new BigDecimal(-100), dateOfDeposit));

        // then
        assertEquals("Error : Le montant doit être positif", exception.getMessage());
    }

    @Test
    void should_add_money_to_the_account() {
        // given
        LocalDateTime dateOfDeposit = of(2023, 5, 3, 10, 0, 0);

        // when
        account.recordDeposit(new BigDecimal(1000), dateOfDeposit);

        // then
        assertEquals(new BigDecimal(1000), account.getCurrentBalance());
    }

    @Test
    void should_withdrawal_money_to_the_account() {
        // given
        LocalDateTime dateOfWithdrawal = of(2023, 5, 3, 10, 1, 0);

        // when
        account.recordWithDrawal(new BigDecimal(1000), dateOfWithdrawal);

        // then
        assertEquals(new BigDecimal(-1000), account.getCurrentBalance());
    }

    @Test
    void should_print_statement() {
        // given
        LocalDateTime dateOfDeposit = of(2023, 5, 3, 10, 0, 0);
        LocalDateTime dateOfWithdrawal = of(2023, 5, 3, 10, 31, 0);
        account.recordDeposit(new BigDecimal(1000), dateOfDeposit);
        account.recordWithDrawal(new BigDecimal(100), dateOfWithdrawal);

        // when
        String result = account.printStatement();

        // then
        assertEquals("  date  |  credit  |  debit  |  balance  \n" +
                                    "  03/05/2023 10:31:00  |    |  -100.00  |  900.00  \n" +
                                    "  03/05/2023 10:00:00  |  1000.00  |    |  1000.00  \n", result);
    }
}

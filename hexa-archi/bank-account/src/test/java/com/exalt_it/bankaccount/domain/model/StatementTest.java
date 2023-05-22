package com.exalt_it.bankaccount.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.exalt_it.bankaccount.domain.model.TransactionType.DEPOSIT;
import static com.exalt_it.bankaccount.domain.model.TransactionType.WITHDRAWAL;
import static java.time.LocalDateTime.of;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StatementTest {
    @Test
    void should_print_deposit() {
        // given
        Statement statement = new Statement(asList(
                new Transaction(new BigDecimal(200), DEPOSIT, of(2023, 5, 3, 10, 0, 0), new BigDecimal(200)),
                new Transaction(new BigDecimal(800), DEPOSIT, of(2023, 5, 4, 10, 23, 0), new BigDecimal(1000)),
                new Transaction(new BigDecimal(400), DEPOSIT, of(2023, 5, 5, 10, 44, 0), new BigDecimal(1400))
        ));

        // when
        String result = statement.print();

        // then
        assertEquals("  date  |  credit  |  debit  |  balance  \n" +
                "  05/05/2023 10:44:00  |  400.00  |    |  1400.00  \n" +
                "  04/05/2023 10:23:00  |  800.00  |    |  1000.00  \n" +
                "  03/05/2023 10:00:00  |  200.00  |    |  200.00  \n", result);
    }

    @Test
    void should_print_withdrawal() {
        // given
        Statement statement = new Statement(asList(
                new Transaction(new BigDecimal(200), WITHDRAWAL, of(2023, 5, 3, 10, 0, 0), new BigDecimal(-200)),
                new Transaction(new BigDecimal(400), WITHDRAWAL, of(2023, 5, 5, 10, 44, 0), new BigDecimal(-600))
        ));

        // when
        String result = statement.print();

        // then
        assertEquals("  date  |  credit  |  debit  |  balance  \n" +
                "  05/05/2023 10:44:00  |    |  400.00  |  -600.00  \n" +
                "  03/05/2023 10:00:00  |    |  200.00  |  -200.00  \n", result);
    }

    @Test
    void should_print_statement() {
        // given
        Statement statement = new Statement(asList(
                new Transaction(new BigDecimal(800), DEPOSIT, of(2023, 5, 5, 10, 23, 0), new BigDecimal(800)),
                new Transaction(new BigDecimal(200), WITHDRAWAL, of(2023, 5, 6, 10, 0, 0), new BigDecimal(600)),
                new Transaction(new BigDecimal(500), DEPOSIT, of(2023, 5, 7, 10, 23, 0), new BigDecimal(1100)),
                new Transaction(new BigDecimal(400), WITHDRAWAL, of(2023, 5, 8, 10, 44, 0), new BigDecimal(700))
        ));

        // when
        String result = statement.print();

        // then
        assertEquals("  date  |  credit  |  debit  |  balance  \n" +
                "  08/05/2023 10:44:00  |    |  400.00  |  700.00  \n" +
                "  07/05/2023 10:23:00  |  500.00  |    |  1100.00  \n" +
                "  06/05/2023 10:00:00  |    |  200.00  |  600.00  \n" +
                "  05/05/2023 10:23:00  |  800.00  |    |  800.00  \n", result);
    }
}

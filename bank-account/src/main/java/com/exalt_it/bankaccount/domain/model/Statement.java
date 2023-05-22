package com.exalt_it.bankaccount.domain.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import static com.exalt_it.bankaccount.domain.model.TransactionType.DEPOSIT;
import static com.exalt_it.bankaccount.domain.model.TransactionType.WITHDRAWAL;

public class Statement {
    private static final String COL_MARGIN = "  ";
    private static final String COL_SEPARATOR = "|";
    private static final String EMPTY_VALUE = "|    |";
    public static final String END_LINE = "\n";
    public static final String STATEMENT_HEADER = "  date  |  credit  |  debit  |  balance  \n";

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
    private final List<Transaction> transactions;

    public Statement(List<Transaction> transactions) {
        this.transactions = transactions;
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
    }

    public String print() {
        StringBuilder result = new StringBuilder(STATEMENT_HEADER);
        transactions.stream()
                .sorted(Comparator.comparing(Transaction::date).reversed())
                .forEach(transaction -> buildLine(result, transaction));
        return result.toString();
    }

    private void buildLine(StringBuilder stringBuilder, Transaction transaction) {
        appendDateTo(stringBuilder, transaction);
        appendIfTransactionIsDeposit(stringBuilder, transaction);
        appendIfTransactionIsWithDrawal(stringBuilder, transaction);
        appendBalance(stringBuilder, transaction);
    }

    private void appendDateTo(StringBuilder stringBuilder, Transaction transaction) {
        stringBuilder.append(COL_MARGIN)
                .append(transaction.date().format(dateFormatter))
                .append(COL_MARGIN);
    }

    private void appendIfTransactionIsDeposit(StringBuilder stringBuilder, Transaction transaction) {
        if (DEPOSIT.equals(transaction.type())) {
            stringBuilder.append(COL_SEPARATOR + COL_MARGIN)
                    .append(decimalFormat.format(transaction.money()))
                    .append(COL_MARGIN)
                    .append(EMPTY_VALUE);
        }
    }

    private void appendIfTransactionIsWithDrawal(StringBuilder stringBuilder, Transaction transaction) {
        if (WITHDRAWAL.equals(transaction.type())) {
            stringBuilder.append(EMPTY_VALUE + COL_MARGIN)
                    .append(decimalFormat.format(transaction.money()))
                    .append(COL_MARGIN)
                    .append(COL_SEPARATOR);
        }
    }

    private void appendBalance(StringBuilder stringBuilder, Transaction transaction) {
        stringBuilder.append(COL_MARGIN)
                .append(decimalFormat.format(transaction.currentBalance()))
                .append(COL_MARGIN)
                .append(END_LINE);
    }
}

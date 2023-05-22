package com.exalt_it.bankaccount.domain.model;

import com.exalt_it.bankaccount.domain.common.guard.Guard;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.exalt_it.bankaccount.domain.model.TransactionType.WITHDRAWAL;

public record Transaction (BigDecimal money, TransactionType type, LocalDateTime date, BigDecimal currentBalance){

    public static Transaction moneyForDeposit(BigDecimal money, TransactionType type, LocalDateTime date, BigDecimal currentBalance) {
        Guard.moneyGuard(money).validateMoney();
        return new Transaction(money, type, date, currentBalance.add(money.abs()));
    }

    public static Transaction moneyForWithDrawal(BigDecimal money, TransactionType type, LocalDateTime date, BigDecimal currentBalance) {
        Guard.moneyGuard(money).validateMoney();
        return new Transaction(money.negate(), type, date, currentBalance.subtract(money.abs()));
    }

    public BigDecimal getAmountValue() {
        return Objects.equals(WITHDRAWAL, type) ? money().abs().negate() : money();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        final Transaction other = (Transaction) object;
        return money.equals(other.money) &&
                type.equals(other.type) &&
                date().equals(other.date) &&
                currentBalance.equals(other.currentBalance);
    }
}

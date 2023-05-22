package com.exalt_it.bankaccount.domain.common.guard;

import com.exalt_it.bankaccount.domain.common.exception.MoneyException;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class MoneyGuard {
    private final BigDecimal money;

    public MoneyGuard(BigDecimal money) {
        this.money = money;
    }

    public void validateMoney() {
        againstNullValue();
        againstNegativeValue();
    }

    private void againstNullValue() {
        if (money == null) {
            throw new MoneyException("Error : Impossible de prendre en compte un montant NULL");
        }
    }

    private void againstNegativeValue() {
        if (ZERO.compareTo(money) > 0) {
            throw new MoneyException("Error : Le montant doit être positif");
        }
    }
}

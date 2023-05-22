package com.exalt_it.bankaccount.domain.model;

import com.exalt_it.bankaccount.domain.common.guard.Guard;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Operation(String email, BigDecimal money, LocalDateTime date) {
    public static Operation of(String email, BigDecimal money, LocalDateTime date) {
        Guard.moneyGuard(money).validateMoney();
        return new Operation(email, money, date);
    }
}

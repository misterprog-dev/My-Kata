package com.exalt_it.bankaccount.domain.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class DepositEvent {
    private final String email;
    private final BigDecimal money;
    private LocalDateTime date;

    public DepositEvent(String email, BigDecimal money) {
        this.email = email;
        this.money = money;
        this.date = now();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }
}

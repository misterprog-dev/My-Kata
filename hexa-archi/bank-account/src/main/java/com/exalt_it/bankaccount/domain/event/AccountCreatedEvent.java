package com.exalt_it.bankaccount.domain.event;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class AccountCreatedEvent {
    private final String email;
    private LocalDateTime date;


    public AccountCreatedEvent(String email) {
        this.email = email;
        this.date = now();
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDate() {
        return date;
    }
}

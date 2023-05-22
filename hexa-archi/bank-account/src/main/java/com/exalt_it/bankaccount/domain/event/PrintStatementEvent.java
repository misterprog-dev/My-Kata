package com.exalt_it.bankaccount.domain.event;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class PrintStatementEvent {
    private final String email;
    private LocalDateTime date;

    public PrintStatementEvent(String email) {
        this.email = email;
        this.date = now();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }
}

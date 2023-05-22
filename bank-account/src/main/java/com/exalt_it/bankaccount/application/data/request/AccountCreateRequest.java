package com.exalt_it.bankaccount.application.data.request;

import jakarta.validation.constraints.NotEmpty;

public class AccountCreateRequest {
    @NotEmpty(message = "Email is required")
    private String email;

    public AccountCreateRequest() {
    }

    public AccountCreateRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

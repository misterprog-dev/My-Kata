package com.exalt_it.bankaccount.application.data.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public abstract class BaseOperationRequest {
    @NotEmpty(message = "Email is required")
    private final String email;

    @NotNull(message = "money is required")
    private final BigDecimal money;

    public BaseOperationRequest(String email, BigDecimal money) {
        this.email = email;
        this.money = money;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getMoney() {
        return money;
    }
}

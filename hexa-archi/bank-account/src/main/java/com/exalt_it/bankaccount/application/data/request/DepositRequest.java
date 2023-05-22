package com.exalt_it.bankaccount.application.data.request;

import java.math.BigDecimal;

public class DepositRequest extends BaseOperationRequest {
    public DepositRequest(String email, BigDecimal money) {
        super(email, money);
    }
}

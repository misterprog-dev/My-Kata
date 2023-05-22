package com.exalt_it.bankaccount.application.data.request;

import java.math.BigDecimal;

public class WithDrawalRequest extends BaseOperationRequest {
    public WithDrawalRequest(String email, BigDecimal money) {
        super(email, money);
    }
}

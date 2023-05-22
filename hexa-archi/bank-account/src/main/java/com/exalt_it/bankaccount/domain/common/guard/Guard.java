package com.exalt_it.bankaccount.domain.common.guard;

import java.math.BigDecimal;

public class Guard {

    public static MoneyGuard moneyGuard(BigDecimal money) {
        return new MoneyGuard(money);
    }
}

package com.exalt_it.bankaccount.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactionForDeposit {
    void recordDeposit(BigDecimal money, LocalDateTime date);
}

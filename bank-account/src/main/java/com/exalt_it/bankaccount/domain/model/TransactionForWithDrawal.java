package com.exalt_it.bankaccount.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactionForWithDrawal {
    void recordWithDrawal(BigDecimal money, LocalDateTime date);
}

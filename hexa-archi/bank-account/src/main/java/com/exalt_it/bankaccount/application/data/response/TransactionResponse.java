package com.exalt_it.bankaccount.application.data.response;

import com.exalt_it.bankaccount.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(BigDecimal money, TransactionType type, LocalDateTime date) {}

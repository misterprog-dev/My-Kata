package com.exalt_it.bankaccount.infrastructure.output.persistence.entity;

import com.exalt_it.bankaccount.domain.model.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hibernate.annotations.OnDeleteAction.CASCADE;

@Entity
@Table(name = TransactionEntity.TABLE_NAME)
@Access(AccessType.FIELD)
public class TransactionEntity {
    public static final String TABLE_NAME = "transaction";
    public static final String TABLE_ID = TABLE_NAME + "_ID";
    private static final String TABLE_SEQ = TABLE_ID + "_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_SEQ)
    @SequenceGenerator(name = TABLE_SEQ, sequenceName = TABLE_SEQ)
    private Long id;

    @NotNull(message = "The money can't be null")
    @Column(name="money", nullable = false)
    private BigDecimal money;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    @NotNull(message = "The type can't be null")
    private TransactionType type;

    @NotNull(message = "The date can't be null")
    @Column(name="date", nullable = false)
    private LocalDateTime date;

    @NotNull(message = "The current_balance can't be null")
    @Column(name="current_balance", nullable = false)
    private BigDecimal currentBalance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = CASCADE)
    private AccountEntity account;

    public TransactionEntity() {
    }

    public TransactionEntity(BigDecimal money, TransactionType type, LocalDateTime date, BigDecimal currentBalance) {
        this.money = money;
        this.type = type;
        this.date = date;
        this.currentBalance = currentBalance;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }
}

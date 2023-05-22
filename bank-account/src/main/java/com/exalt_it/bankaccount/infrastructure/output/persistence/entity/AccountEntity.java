package com.exalt_it.bankaccount.infrastructure.output.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = AccountEntity.TABLE_NAME)
@Access(AccessType.FIELD)
public class AccountEntity extends CustomAuditAbstract {
    public static final String TABLE_NAME = "account";
    public static final String TABLE_ID = TABLE_NAME + "_ID";
    private static final String TABLE_SEQ = TABLE_ID + "_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_SEQ)
    @SequenceGenerator(name = TABLE_SEQ, sequenceName = TABLE_SEQ)
    private Long id;

    @NotNull(message = "The email can't be null")
    @Column(name="email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<TransactionEntity> transactions = new ArrayList<>();

    public AccountEntity() {
    }

    public AccountEntity(String email) {
        this.email = email;
    }

    public void addTransaction(TransactionEntity transaction) {
        transaction.setAccount(this);
        transactions.add(transaction);
    }

    public String getEmail() {
        return email;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }
}

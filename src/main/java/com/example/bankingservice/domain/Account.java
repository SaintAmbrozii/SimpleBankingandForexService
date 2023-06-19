package com.example.bankingservice.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user_accounts")
public class Account implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number")
    private Integer accountNumber;
    private boolean isAktive;
    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance")
    private BigDecimal accountBalance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @Column(name = "currency")
    private Currency currency;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "id",orphanRemoval = true)
    private List<Transactional> transactionals;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "account",orphanRemoval = true)
    private Set<Order> orders;


    public Account(Long id, Integer accountNumber, boolean isAktive, String accountType, BigDecimal accountBalance, User user,Currency currency) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.isAktive = isAktive;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.user = user;
        this.currency = currency;
    }

    public Account() {
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

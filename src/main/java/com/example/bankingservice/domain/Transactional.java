package com.example.bankingservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@Table(name = "transactional")
@AllArgsConstructor
@Builder
public class Transactional {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Account fromAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Account toAccount;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

    public Transactional() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transactional)) return false;
        Transactional that = (Transactional) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

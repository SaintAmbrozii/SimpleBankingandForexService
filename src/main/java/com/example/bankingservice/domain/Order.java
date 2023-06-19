package com.example.bankingservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.engine.profile.Fetch;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal summa;
    private Currency currency;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Account account;

    private Double bayPrice;
    private Double salePrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

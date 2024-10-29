package com.rockville.auth.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_term")
public class PaymentTerm extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private String id;
    @Column(name = "reservation_id")
    private String reservationId;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "terms")
    private Integer terms;
    @Column(name = "months_without_interest")
    private String monthsWithoutInterest;
    @Column(name = "interest")
    private Double interest;
    @Column(name = "price_without_interest")
    private Double priceWithoutInterest;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "start_date")
    private Instant startDate;
    @Column(name = "end_date")
    private Instant endDate;
}

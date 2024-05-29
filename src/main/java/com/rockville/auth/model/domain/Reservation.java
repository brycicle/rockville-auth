package com.rockville.auth.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "lot_id")
    private String lotId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "house_id")
    private String houseId;

    @Column(name = "lot_price")
    private BigDecimal lotPrice;

    @Column(name = "house_price")
    private BigDecimal housePrice;

    @Column(name = "multiplier")
    private BigDecimal multiplier;

    @Column(name = "status")
    private String status;

    @Column(name = "agent_name")
    private String agentName;

    @Column(name = "expiration")
    private Instant expiration;
}

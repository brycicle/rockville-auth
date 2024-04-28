package com.rockville.auth.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "house")
public class House extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "lot_area")
    private BigDecimal lotArea;

    @Column(name = "floor_area")
    private BigDecimal floorArea;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "multiplier")
    private BigDecimal multiplier;

}

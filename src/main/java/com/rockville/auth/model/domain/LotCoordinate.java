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
@Table(name = "lot_coordinate")
public class LotCoordinate extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "lotId")
    private String lotId;

    @Column(name = "coorX")
    private int coorX;

    @Column(name = "coorY")
    private int coorY;

}

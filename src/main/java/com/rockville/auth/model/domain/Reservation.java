package com.rockville.auth.model.domain;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "lotId")
    private String lotId;

    @Column(name = "customerId")
    private String customerId;

    @Column(name = "contractPrice")
    private String contractPrice;

}

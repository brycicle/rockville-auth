package com.rockville.auth.model.domain;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lot_history")
public class LotHistory extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "lot_id")
    private String lotId;

    @Column(name = "action")
    private String action;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "user_id")
    private String userId;
}

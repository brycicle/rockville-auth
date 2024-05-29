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
@Table(name = "reservation_approval")
public class ReservationApproval extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "reservation_id")
    private String reservationId;

    @Column(name = "type")
    private String type;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(name = "role_approver")
    private String roleApprover;
}

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
@Table(name = "reservation_history")
public class ReservationHistory extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "reservation_id")
    private String reservationId;

    @Column(name = "action")
    private String action;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "house_id")
    private String houseId;

    @Column(name = "lot_id")
    private String lotId;

    @Column(name = "reservation_document_id")
    private String reservationDocumentId;

    @Column(name = "reservation_approval_id")
    private String reservationApprovalId;


}

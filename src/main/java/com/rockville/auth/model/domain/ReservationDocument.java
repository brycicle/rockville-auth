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
@Table(name = "reservation_document")
public class ReservationDocument extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "reservation_id")
    private String reservationId;

    @Column(name = "requirement_code")
    private String requirementCode;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_type")
    private String fileType;
}

package com.rockville.auth.model.domain;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "userId", unique = true)
    private String userId;

    @Column(name = "name")
    private String name;
}

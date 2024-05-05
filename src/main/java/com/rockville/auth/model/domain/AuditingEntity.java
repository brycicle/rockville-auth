package com.rockville.auth.model.domain;

import com.rockville.auth.model.dto.UserDetailsDto;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;
import java.util.Optional;
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public class AuditingEntity extends Model {
    @CreationTimestamp
    private Instant createdAt = Instant.now();

    private String createdBy;

    @UpdateTimestamp
    private Instant updatedAt = Instant.now();

    private String updatedBy;

    @PrePersist
    public void onCreate() {
        UserDetailsDto user = (UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        setCreatedBy(
                Optional.ofNullable(user).map(UserDetailsDto::getUsername).orElse("Admin")
        );
        setCreatedAt(Instant.now());
        onUpdate();
    }

    @PreUpdate
    public void onUpdate() {
        UserDetailsDto user = (UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        setUpdatedBy(
                Optional.ofNullable(user).map(UserDetailsDto::getUsername).orElse("Admin")
        );
        setUpdatedAt(Instant.now());
    }
}

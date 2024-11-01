package com.rockville.auth.model.domain;

import com.rockville.auth.model.dto.UserDetailsDto;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;
import java.util.Optional;
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class AuditingEntity extends Model {
    @CreationTimestamp
    private Instant createdAt = Instant.now();

    private String createdBy;

    @UpdateTimestamp
    private Instant updatedAt = Instant.now();

    private String updatedBy;

    @PrePersist
    public void onCreate() {
        UserDetailsDto user;
        String username = "";
        try {
            user = (UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            username = Optional.ofNullable(user).map(UserDetailsDto::getUsername).orElse("Admin");
        } catch (Exception e) {
            log.error(e.getMessage());
            username = "Admin";
        }
        setCreatedBy(username);
        setCreatedAt(Instant.now());
        onUpdate();
    }

    @PreUpdate
    public void onUpdate() {
        UserDetailsDto user;
        String username = "";
        try {
            user = (UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            username = Optional.ofNullable(user).map(UserDetailsDto::getUsername).orElse("Admin");
        } catch (Exception e) {
            log.error(e.getMessage());
            username = "Admin";
        }
        setUpdatedBy(username);
        setUpdatedAt(Instant.now());
    }
}

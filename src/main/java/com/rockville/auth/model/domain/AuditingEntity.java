package com.rockville.auth.model.domain;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
//        UserJson userJson = UserJson.fromContext();

//        setCreatedBy(Optional.ofNullable(userJson).map(UserJson::getUsername).orElse("admin"));
        setCreatedBy("Admin");
        setCreatedAt(Instant.now());

        onUpdate();
    }

    @PreUpdate
    public void onUpdate() {
//        UserJson tokenUser = UserJson.fromContext();

//        setUpdatedBy(Optional.ofNullable(tokenUser).map(UserJson::getUsername).orElse("admin"));
        setUpdatedBy("Admin");
        setUpdatedAt(Instant.now());
    }
}

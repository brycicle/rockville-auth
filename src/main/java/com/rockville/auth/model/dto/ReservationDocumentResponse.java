package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Builder
@Data
public class ReservationDocumentResponse {
    private String id;
    private String reservationId;
    private String requirementCode;
    private String requirementName;
    private String filePath;
    private String fileType;

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}

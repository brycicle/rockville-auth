package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
public class ReservationDocumentRequest {
    private MultipartFile file;
    private String reservationId;
    private String requirementCode;
    private String filePath;
    private String fileType;
}

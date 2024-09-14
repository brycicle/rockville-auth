package com.rockville.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FileDocumentResponse {
    private String id;
    private String name;
    private String folder;
    private String filePath;

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}

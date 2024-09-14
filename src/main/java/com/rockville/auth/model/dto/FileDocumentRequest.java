package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FileDocumentRequest {

    private String name;
    private String folder;
    private String filePath;

}

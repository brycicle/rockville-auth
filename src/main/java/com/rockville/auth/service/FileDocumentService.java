package com.rockville.auth.service;

import com.rockville.auth.model.dto.FileDocumentRequest;
import com.rockville.auth.model.dto.FileDocumentResponse;
import com.rockville.auth.model.dto.HouseRequest;
import com.rockville.auth.model.dto.HouseResponse;

import java.util.List;

public interface FileDocumentService {
    List<FileDocumentResponse> getDocuments();
    List<FileDocumentResponse> saveDocuments(List<FileDocumentRequest> requests);
}

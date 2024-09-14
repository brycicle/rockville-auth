package com.rockville.auth.controller;

import com.rockville.auth.model.dto.BaseResponse;
import com.rockville.auth.model.dto.FileDocumentRequest;
import com.rockville.auth.model.dto.FileDocumentResponse;
import com.rockville.auth.service.FileDocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/file")
@RequiredArgsConstructor
public class FileDocumentController {

    private final FileDocumentService fileDocumentService;

    //    @PostMapping
//    public BaseResponse<HouseResponse> createHouse(@RequestBody HouseRequest request) {
//        log.info("HouseController - createHouse {}", request);
//        return new BaseResponse<>(
//                houseService.createHouse(request)
//        );
//    }
    @GetMapping
    public BaseResponse<List<FileDocumentResponse>> getDocuments() {
        log.info("FileDocumentController - getDocuments");
        return new BaseResponse<>(fileDocumentService.getDocuments());
    }

    @PostMapping
    public BaseResponse<List<FileDocumentResponse>> saveDocuments(@RequestBody List<FileDocumentRequest> requests) {
        log.info("FileDocumentController - saveDocuments : {}", requests);
        return new BaseResponse<>(fileDocumentService.saveDocuments(requests));
    }
}
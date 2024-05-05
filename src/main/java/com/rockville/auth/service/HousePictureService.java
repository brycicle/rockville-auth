package com.rockville.auth.service;

import com.rockville.auth.model.dto.HouseDetailResponse;
import com.rockville.auth.model.dto.HousePictureResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HousePictureService {
    List<HousePictureResponse> getPictures(String houseId);
    HouseDetailResponse saveHousePicture(MultipartFile file);
}

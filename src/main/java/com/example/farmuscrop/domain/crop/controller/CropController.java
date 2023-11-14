package com.example.farmuscrop.domain.crop.controller;

import com.example.farmuscrop.domain.crop.document.Crop;
import com.example.farmuscrop.domain.crop.dto.req.CreateCropRequestDto;
import com.example.farmuscrop.domain.crop.service.CropService;
import com.example.farmuscrop.global.response.BaseResponseDto;
import com.example.farmuscrop.global.response.ErrorMessage;
import com.example.farmuscrop.global.response.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/crop")
@RequiredArgsConstructor
public class CropController {

    private final CropService cropService;

    @PostMapping("")
    public BaseResponseDto<?> createCrop(
            @RequestBody CreateCropRequestDto requestDto
    ) {
        if (cropService.checkCropPresent(requestDto.getName())) {
            return BaseResponseDto.of(ErrorMessage.INTERVAL_SERVER_ERROR);
        } else {
            return BaseResponseDto.of(SuccessMessage.SUCCESS, cropService.saveCrop(requestDto));
        }
    }

    @GetMapping("")
    public BaseResponseDto<?> getCropInfo() {
        return BaseResponseDto.of(SuccessMessage.SUCCESS, cropService.getCropInfoList());
    }

    @GetMapping("/{id}")
    public BaseResponseDto<?> getCropSteps(
            @PathVariable String id
    ) {
        List<Crop.Step> steps = cropService.getSteps(new ObjectId(id));
        if (steps == null) {
            return BaseResponseDto.of(ErrorMessage.INTERVAL_SERVER_ERROR);
        } else {
            return BaseResponseDto.of(SuccessMessage.SUCCESS, steps);
        }
    }
}

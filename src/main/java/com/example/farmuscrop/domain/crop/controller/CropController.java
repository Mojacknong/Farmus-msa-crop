package com.example.farmuscrop.domain.crop.controller;

import com.example.farmuscrop.domain.crop.document.Crop;
import com.example.farmuscrop.domain.crop.dto.req.CreateCropRequestDto;
import com.example.farmuscrop.domain.crop.service.CropService;
import com.example.farmuscrop.global.response.BaseResponseDto;
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
            return new BaseResponseDto<>(400, false, "이미 존재하는 작물입니다.");
        } else {
            return new BaseResponseDto<>(cropService.saveCrop(requestDto));
        }
    }

    @GetMapping("")
    public BaseResponseDto<?> getCropInfo() {
        return new BaseResponseDto<>(cropService.getCropInfoList());
    }

    @GetMapping("/{id}")
    public BaseResponseDto<?> getCropSteps(
            @PathVariable String id
    ) {
        List<Crop.Step> steps = cropService.getSteps(new ObjectId(id));
        if (steps == null) {
            return new BaseResponseDto<>(400, false, "해당 작물이 존재하지 않습니다.");
        } else {
            return new BaseResponseDto<>(steps);
        }
    }
}

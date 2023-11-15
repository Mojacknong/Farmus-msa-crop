package com.example.farmuscrop.domain.crop.controller;

import com.example.farmuscrop.domain.crop.document.VeggieInfo;
import com.example.farmuscrop.domain.crop.dto.req.CreateVeggieInfoRequestDto;
import com.example.farmuscrop.domain.crop.service.VeggieInfoService;
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
public class VeggieInfoController {

    private final VeggieInfoService veggieInfoService;

    @PostMapping("")
    public BaseResponseDto<?> createVeggieInfo(
            @RequestBody CreateVeggieInfoRequestDto requestDto
    ) {
        if (veggieInfoService.checkVeggieInfoPresent(requestDto.getName())) {
            return BaseResponseDto.of(ErrorMessage.INTERVAL_SERVER_ERROR);
        } else {
            return BaseResponseDto.of(SuccessMessage.SUCCESS, veggieInfoService.saveVeggieInfo(requestDto));
        }
    }

    @GetMapping("")
    public BaseResponseDto<?> getVeggieInfo() {
        return BaseResponseDto.of(SuccessMessage.SUCCESS, veggieInfoService.getCropInfoList());
    }

    @GetMapping("/{id}")
    public BaseResponseDto<?> getVeggieInfoSteps(
            @PathVariable String id
    ) {
        List<VeggieInfo.Step> steps = veggieInfoService.getSteps(new ObjectId(id));
        if (steps == null) {
            return BaseResponseDto.of(ErrorMessage.INTERVAL_SERVER_ERROR);
        } else {
            return BaseResponseDto.of(SuccessMessage.SUCCESS, steps);
        }
    }
}

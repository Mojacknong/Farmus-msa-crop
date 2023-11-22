package com.example.farmuscrop.domain.crop.controller;

import com.example.farmuscrop.domain.crop.document.VeggieInfo;
import com.example.farmuscrop.domain.crop.dto.req.CreateVeggieInfoRequestDto;
import com.example.farmuscrop.domain.crop.dto.res.GetStepNameResponseDto;
import com.example.farmuscrop.domain.crop.dto.res.GetVeggieInfoResponseDto;
import com.example.farmuscrop.domain.crop.service.VeggieInfoService;
import com.example.farmuscrop.global.response.BaseResponseDto;
import com.example.farmuscrop.global.response.ErrorMessage;
import com.example.farmuscrop.global.response.SuccessMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/crop")
@RequiredArgsConstructor
@Slf4j
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

    @GetMapping("/{id}/info")
    public BaseResponseDto<?> getStepsWithTip(
            @PathVariable String id
    ) {
        return BaseResponseDto.of(SuccessMessage.SUCCESS, veggieInfoService.getStepsWithTip(new ObjectId(id)));
    }

    @GetMapping("/{id}/info/{step}")
    public BaseResponseDto<?> getStepsWithTip(
            @PathVariable String id,
            @PathVariable int step
    ) {
        return BaseResponseDto.of(SuccessMessage.SUCCESS, veggieInfoService.getStepsWithTip(new ObjectId(id), step));
    }

    @GetMapping("/{id}/steps")
    public BaseResponseDto<?> getVeggieInfoStepList(
            @PathVariable String id
    ) {
        return BaseResponseDto.of(SuccessMessage.SUCCESS, veggieInfoService.getAllStepName(new ObjectId(id)));
    }

    @GetMapping("/info/{id}")
    public GetVeggieInfoResponseDto getVeggieInfoDetail(
            @PathVariable String id
    ) {
        log.info("id: {}", id);
        return veggieInfoService.getVeggieInfoDetail(new ObjectId(id));
    }

    @GetMapping("/{id}/step/{step}")
    public GetStepNameResponseDto getVeggieInfoStepName(
            @PathVariable String id,
            @PathVariable int step
    ) {
        log.info("id: {}, step: {}", id, step);
        return veggieInfoService.getVeggieInfoStepName(new ObjectId(id), step);
    }
}

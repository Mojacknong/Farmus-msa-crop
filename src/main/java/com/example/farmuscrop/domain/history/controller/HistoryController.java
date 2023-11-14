package com.example.farmuscrop.domain.history.controller;

import com.example.farmuscrop.common.JwtTokenProvider;
import com.example.farmuscrop.domain.history.dto.req.CreateHistoryDetailRequestDto;
import com.example.farmuscrop.domain.history.dto.req.CreateHistoryRequestDto;
import com.example.farmuscrop.domain.history.dto.req.UpdateResultRequestDto;
import com.example.farmuscrop.domain.history.service.HistoryService;
import com.example.farmuscrop.global.response.BaseResponseDto;
import com.example.farmuscrop.global.response.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/crop/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("")
    public BaseResponseDto<?> createUserHistory(
            @RequestBody CreateHistoryRequestDto requestDto
    ) {
        return BaseResponseDto.of(SuccessMessage.CREATED, historyService.createUserHistory(requestDto.getUserId()));
    }

    @GetMapping("")
    public BaseResponseDto<?> getHistory(HttpServletRequest request) {
        Long userId = Long.valueOf(jwtTokenProvider.getUserId(request));

        return BaseResponseDto.of(SuccessMessage.SUCCESS, historyService.getUserHistoryDetails(userId));
    }

    @PostMapping("/detail")
    public BaseResponseDto<?> createUserHistoryDetail(
            HttpServletRequest request,
            @RequestBody CreateHistoryDetailRequestDto requestDto
    ) {
        Long userId = Long.valueOf(jwtTokenProvider.getUserId(request));

        return BaseResponseDto.of(SuccessMessage.CREATED, historyService.createUserHistoryDetail(userId, requestDto));
    }

    @GetMapping("/detail/{historyDetailId}")
    public BaseResponseDto<?> getHistoryDetail(
            @PathVariable String historyDetailId
    ) {
        return BaseResponseDto.of(SuccessMessage.SUCCESS, historyService.getUserHistoryDetail(new ObjectId(historyDetailId)));
    }

    @PatchMapping("/detail")
    public BaseResponseDto<?> updateDetailResult(
            @RequestBody UpdateResultRequestDto requestDto
    ) {

        return BaseResponseDto.of(SuccessMessage.SUCCESS, historyService.updateDetailResult(requestDto));
    }
}

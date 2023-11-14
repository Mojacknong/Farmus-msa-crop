package com.example.farmuscrop.domain.history.controller;

import com.example.farmuscrop.domain.history.dto.req.CreateHistoryRequestDto;
import com.example.farmuscrop.domain.history.service.HistoryService;
import com.example.farmuscrop.global.response.BaseResponseDto;
import com.example.farmuscrop.global.response.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crop/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @PostMapping("")
    public BaseResponseDto<?> createUserHistory(
            @RequestBody CreateHistoryRequestDto requestDto
    ) {
        return BaseResponseDto.of(SuccessMessage.CREATED, historyService.createUserHistory(requestDto.getUserId()));
    }
}

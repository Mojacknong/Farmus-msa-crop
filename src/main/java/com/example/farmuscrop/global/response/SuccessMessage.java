package com.example.farmuscrop.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
public enum SuccessMessage {
    SUCCESS(200, "요청에 성공하였습니다."),
    CREATED(201, "생성에 성공하였습니다.");

    private final int code;
    private final String message;

    SuccessMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

}

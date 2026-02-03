package com.mealgo.utils.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    private final String timestamp;
    private final String path;
    private final int status;
    private final String error;
    private final String message;

    public ErrorResponse(String path, int status, String error, String message) {
        this.timestamp = LocalDateTime.now().toString(); // 발생 시간
        this.path = path;   // 요청 URL
        this.status = status; // HTTP 상태 코드
        this.error = error;   // 에러 유형
        this.message = message; // 상세 메시지
    }
}

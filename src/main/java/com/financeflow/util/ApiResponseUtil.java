package com.financeflow.util;

import com.financeflow.response.ApiResponse;

import java.time.LocalDateTime;

public class ApiResponseUtil {

    private ApiResponseUtil() {
    }

    public static <T> ApiResponse<T> success(String message, T data) {

        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
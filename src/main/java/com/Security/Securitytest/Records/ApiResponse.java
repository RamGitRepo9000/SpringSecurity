package com.Security.Securitytest.Records;

public record ApiResponse<T>(
        String message,
        int status,
        T data
) {
}

package com.example.revise.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public abstract class ApiResponse<T> {
    int status;
    String error;
    T data;

    public ApiResponse(int status, String error, T data) {
        this.status = status;
        this.error = error;
        this.data = data;
    }

    public static final int OK = 0;
    public static final int BAD_REQUEST = 1;
    public static final int UNAUTHORIZED = 2;
    public static final int FORBIDDEN = 3;
    public static final int NOT_FOUND = 4;
    public static final int CONFLICT = 5;
    public static final int INTERNAL_SERVER_ERROR = -1;
    public static final int UNKNOWN = -9;
}

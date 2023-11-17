package com.hills.backend.model;

public class ApiErrorResponse {
    public int status;

    public String error;

    public ApiErrorResponse(int status, String error) {
        this.status = status;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }
}

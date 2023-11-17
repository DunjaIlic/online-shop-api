package com.hills.backend.utils;

import com.hills.backend.model.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiHelper {
    public static ResponseEntity HandleApiErrorResponse(Exception e) {
        if(e instanceof IllegalArgumentException) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiErrorResponse(400, e.getMessage()));
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponse(500, "Internal error."));
    }
}

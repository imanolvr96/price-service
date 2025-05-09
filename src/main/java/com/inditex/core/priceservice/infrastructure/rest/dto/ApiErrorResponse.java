package com.inditex.core.priceservice.infrastructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Schema(description = "Details about an API error response")
public class ApiErrorResponse {
    
    @Schema(description = "Error message", example = "Invalid request parameters.")
    private String message;

    @Schema(description = "HTTP status code", example = "400")
    private int status;

    @Schema(description = "Timestamp when the error occurred", example = "2025-05-08T14:23:45")
    private LocalDateTime timestamp;

    public ApiErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
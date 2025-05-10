package com.inditex.core.priceservice.infrastructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Details about an API error response.
 *
 * <p>This class is used to represent an error response returned from the API.
 * It includes the error message, the HTTP status code, and the timestamp when the error occurred.
 * The class is annotated with Swagger's {@link Schema} annotations to document it for the API documentation.</p>
 *
 * @author Imanol Villalba Rodríguez
 * @date 10/05/2025
 */
@Setter
@Getter
@Schema(description = "Details about an API error response")
public class ApiErrorResponse {

    /**
     * The error message describing the issue.
     *
     * <p>This message provides information about what went wrong in the request.</p>
     */
    @Schema(description = "Error message", example = "Invalid request parameters.")
    private String message;

    /**
     * The HTTP status code indicating the type of error.
     *
     * <p>This is typically a 4xx or 5xx code depending on whether the error is client-side or server-side.</p>
     */
    @Schema(description = "HTTP status code", example = "400")
    private int status;

    /**
     * The timestamp when the error occurred.
     *
     * <p>This timestamp provides the exact moment when the error response was generated, formatted in ISO 8601.</p>
     */
    @Schema(description = "Timestamp when the error occurred", example = "2025-05-08T14:23:45")
    private LocalDateTime timestamp;

    /**
     * Constructor for creating an ApiErrorResponse.
     *
     * @param message The error message to be returned.
     * @param status  The HTTP status code associated with the error.
     */
    public ApiErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
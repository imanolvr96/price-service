package com.inditex.core.priceservice.infrastructure.rest.exception;

import com.inditex.core.priceservice.domain.exception.PriceNotFoundException;
import com.inditex.core.priceservice.infrastructure.rest.dto.ApiErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.util.Map;

/**
 * Global exception handler for handling different types of exceptions in the REST API.
 * <p>
 * This class is annotated with {@link RestControllerAdvice} to handle exceptions globally across all REST endpoints.
 * It provides customized responses for various exceptions that may occur during the execution of the API requests.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link PriceNotFoundException} when no applicable price is found for the given product and brand.
     * <p>
     * This method catches the {@link PriceNotFoundException} and returns a response with HTTP status 404 (Not Found),
     * along with an error message describing the issue.
     * </p>
     *
     * @param ex the exception thrown when no price is found
     * @return a {@link ResponseEntity} with an {@link ApiErrorResponse} containing the error message and status
     */
    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlePriceNotFound(PriceNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Handles {@link MethodArgumentNotValidException} when request parameters fail validation.
     * <p>
     * This method catches validation errors, aggregates them, and returns a response with HTTP status 400 (Bad Request),
     * along with a detailed error message describing which parameters failed validation.
     * </p>
     *
     * @param ex the exception thrown when method argument validation fails
     * @return a {@link ResponseEntity} with an {@link ApiErrorResponse} containing the validation error message and status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder("Invalid request parameters: ");
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMessage.append(String.format("Field '%s' %s; ", fieldError.getField(), fieldError.getDefaultMessage()));
        });

        ApiErrorResponse response = new ApiErrorResponse(errorMessage.toString(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handles {@link ConstraintViolationException} when a request parameter violates a constraint.
     * <p>
     * This method catches constraint violations (e.g., invalid range or format) and returns a response with HTTP status 400
     * (Bad Request) along with a message describing the violation.
     * </p>
     *
     * @param ex the exception thrown when a constraint violation occurs
     * @return a {@link ResponseEntity} with an {@link ApiErrorResponse} containing the error message and status
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        ApiErrorResponse response = new ApiErrorResponse("Validation error: " + ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handles {@link HttpMessageNotReadableException} when the request body is not readable or malformed.
     * <p>
     * This method catches situations where the request body cannot be parsed (e.g., malformed JSON) and returns a response
     * with HTTP status 400 (Bad Request), along with an error message describing the issue.
     * </p>
     *
     * @param ex the exception thrown when the request body is not readable
     * @return a {@link ResponseEntity} with an {@link ApiErrorResponse} containing the error message and status
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleUnreadable(HttpMessageNotReadableException ex) {
        ApiErrorResponse response = new ApiErrorResponse("Malformed request: " + ex.getMostSpecificCause().getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handles {@link MissingServletRequestParameterException} when a required request parameter is missing.
     * <p>
     * This method catches situations where a required parameter is missing from the request and returns a response
     * with HTTP status 400 (Bad Request), indicating which parameter is missing.
     * </p>
     *
     * @param ex the exception thrown when a required parameter is missing
     * @return a {@link ResponseEntity} with an {@link ApiErrorResponse} containing the error message and status
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
        ApiErrorResponse response = new ApiErrorResponse("Missing required parameter: " + ex.getParameterName(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handles any generic {@link Exception} that is not explicitly handled by other exception handlers.
     * <p>
     * This method catches unexpected errors and returns a response with HTTP status 500 (Internal Server Error),
     * along with an error message describing the issue.
     * </p>
     *
     * @param ex the exception thrown when an unexpected error occurs
     * @return a {@link ResponseEntity} with an {@link ApiErrorResponse} containing the error message and status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(Exception ex) {
        ApiErrorResponse response = new ApiErrorResponse("Unexpected error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Handles {@link SQLException} when there is a database error during price data retrieval.
     * <p>
     * This method catches SQL exceptions (e.g., errors in database queries) and returns a response with HTTP status 500
     * (Internal Server Error), along with a message describing the database error.
     * </p>
     *
     * @param ex the exception thrown when a database error occurs
     * @return a {@link ResponseEntity} with an {@link ApiErrorResponse} containing the error message and status
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ApiErrorResponse> handleDatabaseError(SQLException ex) {
        ApiErrorResponse response = new ApiErrorResponse("Error occurred while fetching price data: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Handles {@link MethodArgumentTypeMismatchException} when a request parameter has an invalid type.
     * <p>
     * This method catches situations where a parameter does not match the expected type (e.g., String instead of Integer)
     * and returns a response with HTTP status 400 (Bad Request), including a message detailing the mismatch.
     * </p>
     *
     * @param ex the exception thrown when a parameter type mismatch occurs
     * @return a {@link ResponseEntity} with an error message indicating the type mismatch
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = String.format(
                "Invalid value for parameter '%s': %s", ex.getName(), ex.getValue()
        );
        return ResponseEntity.badRequest().body(Map.of("message", message));
    }
}
package com.inditex.core.priceservice.unit.infrastructure.rest.exception;

import com.inditex.core.priceservice.domain.exception.PriceNotFoundException;
import com.inditex.core.priceservice.infrastructure.rest.dto.ApiErrorResponse;
import com.inditex.core.priceservice.infrastructure.rest.exception.GlobalExceptionHandler;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandlePriceNotFoundException() {
        Integer productId = 1;
        Integer brandId = 99;
        LocalDateTime applicationDate = LocalDateTime.of(2025, 5, 11, 10, 0, 0, 0);

        PriceNotFoundException exception = new PriceNotFoundException(productId, brandId, applicationDate);

        ResponseEntity<ApiErrorResponse> responseEntity = globalExceptionHandler.handlePriceNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        assertNotNull(responseEntity.getBody());
        assertEquals(404, responseEntity.getBody().getStatus());
    }

    @Test
    void testHandleValidation() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "fieldName", "defaultMessage");

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

        ResponseEntity<ApiErrorResponse> responseEntity = globalExceptionHandler.handleValidation(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().getMessage().contains("Invalid request parameters"));
        assertTrue(responseEntity.getBody().getMessage().contains("Field 'fieldName' defaultMessage"));
        assertEquals(400, responseEntity.getBody().getStatus());
    }

    @Test
    void testHandleConstraintViolation() {
        ConstraintViolationException exception = mock(ConstraintViolationException.class);
        String violationMessage = "Invalid value for field 'fieldName'";

        when(exception.getMessage()).thenReturn(violationMessage);

        ResponseEntity<ApiErrorResponse> responseEntity = globalExceptionHandler.handleConstraintViolation(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().getMessage().contains("Validation error"));
        assertTrue(responseEntity.getBody().getMessage().contains(violationMessage));
        assertEquals(400, responseEntity.getBody().getStatus());
    }

    @Test
    void testHandleUnreadable() {
        HttpMessageNotReadableException exception = mock(HttpMessageNotReadableException.class);
        String errorMessage = "Invalid JSON format";

        when(exception.getMostSpecificCause()).thenReturn(new Throwable(errorMessage));

        ResponseEntity<ApiErrorResponse> responseEntity = globalExceptionHandler.handleUnreadable(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().getMessage().contains("Malformed request"));
        assertTrue(responseEntity.getBody().getMessage().contains(errorMessage));
        assertEquals(400, responseEntity.getBody().getStatus());
    }

    @Test
    void testHandleMissingParams() {
        MissingServletRequestParameterException exception = mock(MissingServletRequestParameterException.class);
        String missingParameter = "productId";

        when(exception.getParameterName()).thenReturn(missingParameter);

        ResponseEntity<ApiErrorResponse> responseEntity = globalExceptionHandler.handleMissingParams(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().getMessage().contains("Missing required parameter"));
        assertTrue(responseEntity.getBody().getMessage().contains(missingParameter));
        assertEquals(400, responseEntity.getBody().getStatus());
    }

    @Test
    void testHandleDatabaseError() {
        SQLException exception = mock(SQLException.class);
        String errorMessage = "Database connection failed";

        when(exception.getMessage()).thenReturn(errorMessage);

        ResponseEntity<ApiErrorResponse> responseEntity = globalExceptionHandler.handleDatabaseError(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().getMessage().contains("Error occurred while fetching price data"));
        assertTrue(responseEntity.getBody().getMessage().contains(errorMessage));
        assertEquals(500, responseEntity.getBody().getStatus());
    }

    @Test
    void testHandleTypeMismatch() {
        MethodArgumentTypeMismatchException exception = mock(MethodArgumentTypeMismatchException.class);
        String parameterName = "productId";
        String invalidValue = "abc";

        when(exception.getName()).thenReturn(parameterName);
        when(exception.getValue()).thenReturn(invalidValue);

        ResponseEntity<?> responseEntity = globalExceptionHandler.handleTypeMismatch(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        @SuppressWarnings("unchecked")
        Map<String, String> responseBody = (Map<String, String>) responseEntity.getBody();

        assertTrue(responseBody.get("message").contains("Invalid value for parameter"));
        assertTrue(responseBody.get("message").contains(parameterName));
        assertTrue(responseBody.get("message").contains(invalidValue));
    }


    @Test
    void testDataAccessException() {
        DataAccessException exception = mock(DataAccessException.class);

        ResponseEntity<ApiErrorResponse> responseEntity = globalExceptionHandler.dataAccessException(exception);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("A database access error occurred", responseEntity.getBody().getMessage());
        assertEquals(503, responseEntity.getBody().getStatus());
    }

    @Test
    void testHandleBadRequest() {
        IllegalArgumentException exception = mock(IllegalArgumentException.class);
        String errorMessage = "Invalid input provided";

        when(exception.getMessage()).thenReturn(errorMessage);

        ResponseEntity<ApiErrorResponse> responseEntity = globalExceptionHandler.handleBadRequest(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(errorMessage, responseEntity.getBody().getMessage());
        assertEquals(400, responseEntity.getBody().getStatus());
    }

    @Test
    void testHandleGeneric() {
        Exception exception = mock(Exception.class);
        String errorMessage = "Unexpected error occurred";

        when(exception.getMessage()).thenReturn(errorMessage);

        ResponseEntity<ApiErrorResponse> responseEntity = globalExceptionHandler.handleGeneric(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Unexpected error: " + errorMessage, responseEntity.getBody().getMessage());
        assertEquals(500, responseEntity.getBody().getStatus());
    }
}
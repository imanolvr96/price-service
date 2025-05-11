package com.inditex.core.priceservice.unit.application.validation;

import com.inditex.core.priceservice.application.validation.PriceValidator;
import com.inditex.core.priceservice.domain.exception.PriceNotFoundException;
import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PriceValidatorTest {

    private PriceValidator priceValidator;

    @BeforeEach
    void setUp() {
        priceValidator = new PriceValidator();
    }

    @Test
    void testValidatePriceFound_PriceIsNotNull() {
        PriceEntity price = new PriceEntity();
        Integer productId = 1;
        Integer brandId = 1;
        LocalDateTime applicationDate = LocalDateTime.now();

        assertDoesNotThrow(() -> priceValidator.validatePriceFound(price, productId, brandId, applicationDate));
    }

    @Test
    void testValidatePriceFound_PriceIsNull() {
        Integer productId = 1;
        Integer brandId = 1;
        LocalDateTime applicationDate = LocalDateTime.now();

        PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () -> priceValidator.validatePriceFound(null, productId, brandId, applicationDate));

        String expectedMessage = String.format("No price found for productId=%d, brandId=%d, date=%s", productId, brandId, applicationDate);
        assertEquals(expectedMessage, exception.getMessage());
    }
}
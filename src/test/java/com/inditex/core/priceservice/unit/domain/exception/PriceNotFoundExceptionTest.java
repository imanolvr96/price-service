package com.inditex.core.priceservice.unit.domain.exception;

import com.inditex.core.priceservice.domain.exception.PriceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PriceNotFoundExceptionTest {

    @Test
    void testPriceNotFoundExceptionMessage() {
        Integer productId = 1;
        Integer brandId = 99;
        LocalDateTime applicationDate = LocalDateTime.of(2025, 5, 11, 10, 0, 0, 0);

        PriceNotFoundException exception = new PriceNotFoundException(productId, brandId, applicationDate);

        String expectedMessage = String.format("No price found for productId=%d, brandId=%d, date=%s", productId, brandId, applicationDate);
        assertEquals(expectedMessage, exception.getMessage());
    }
}

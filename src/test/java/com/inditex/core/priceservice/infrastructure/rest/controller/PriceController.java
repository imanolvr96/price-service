package com.inditex.core.priceservice.infrastructure.rest.controller;

import com.inditex.core.priceservice.application.service.PriceService;
import com.inditex.core.priceservice.infrastructure.rest.dto.PriceResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    private LocalDateTime applicationDate;

    @BeforeEach
    void setUp() {
        applicationDate = LocalDateTime.of(2025, 5, 10, 10, 0, 0, 0);
    }

    @Test
    void getPrice_ShouldReturnPrice_WhenValidRequest() {
        PriceResponseDto priceResponse = PriceResponseDto.builder()
                .productId(1)
                .brandId(1)
                .priceList(2)
                .startDate(LocalDateTime.of(2025, 5, 10, 9, 0, 0, 0))
                .endDate(LocalDateTime.of(2025, 5, 10, 18, 0, 0, 0))
                .price(100.0)
                .build();

        when(priceService.getPrice(applicationDate, 1, 1)).thenReturn(priceResponse);

        ResponseEntity<PriceResponseDto> response = priceController.getPrice(1, 1, applicationDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(100.0, response.getBody().getPrice());
        assertEquals(1, response.getBody().getProductId());
        assertEquals(1, response.getBody().getBrandId());
        assertEquals(2, response.getBody().getPriceList());
    }
}
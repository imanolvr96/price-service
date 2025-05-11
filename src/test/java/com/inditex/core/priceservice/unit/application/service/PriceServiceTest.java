package com.inditex.core.priceservice.unit.application.service;

import com.inditex.core.priceservice.application.mapper.PriceMapper;
import com.inditex.core.priceservice.application.service.PriceService;
import com.inditex.core.priceservice.application.validation.PriceValidator;
import com.inditex.core.priceservice.domain.repository.PriceRepository;
import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import com.inditex.core.priceservice.infrastructure.rest.dto.PriceResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper priceMapper;

    @Mock
    private PriceValidator priceValidator;

    @InjectMocks
    private PriceService priceService;

    private PriceEntity priceEntity;
    private PriceResponseDto priceResponseDto;

    @BeforeEach
    void setUp() {
        priceEntity = new PriceEntity();
        priceEntity.setPrice(100.0);

        priceResponseDto = new PriceResponseDto();
        priceResponseDto.setPrice(100.0);
    }

    @Test
    void testGetPrice() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 1;
        Integer brandId = 1;

        when(priceRepository.findApplicablePrice(applicationDate, productId, brandId)).thenReturn(priceEntity);
        when(priceMapper.toDto(priceEntity)).thenReturn(priceResponseDto);

        PriceResponseDto result = priceService.getPrice(applicationDate, productId, brandId);

        verify(priceRepository).findApplicablePrice(applicationDate, productId, brandId);
        verify(priceValidator).validatePriceFound(priceEntity, productId, brandId, applicationDate);
        verify(priceMapper).toDto(priceEntity);

        assertNotNull(result);
        assertEquals(priceResponseDto.getPrice(), result.getPrice());
    }
}

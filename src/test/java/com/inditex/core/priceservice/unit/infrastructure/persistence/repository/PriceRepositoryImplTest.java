package com.inditex.core.priceservice.unit.infrastructure.persistence.repository;

import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import com.inditex.core.priceservice.infrastructure.persistence.repository.JpaPriceRepository;
import com.inditex.core.priceservice.infrastructure.persistence.repository.PriceRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryImplTest {

    @Mock
    private JpaPriceRepository jpaPriceRepository;

    @InjectMocks
    private PriceRepositoryImpl priceRepositoryImpl;

    @BeforeEach
    void setUp() {
        priceRepositoryImpl = new PriceRepositoryImpl(jpaPriceRepository);
    }

    @Test
    void testFindApplicablePrice() {
        PriceEntity mockedPrice = new PriceEntity();
        mockedPrice.setProductId(1);
        mockedPrice.setBrandId(1);
        mockedPrice.setPrice(100.0);
        mockedPrice.setCurrency("EUR");

        when(jpaPriceRepository.findApplicablePrice(any(), eq(1), eq(1))).thenReturn(mockedPrice);

        PriceEntity result = priceRepositoryImpl.findApplicablePrice(LocalDateTime.now(), 1, 1);

        assertNotNull(result);
        assertEquals(100.0, result.getPrice());
        assertEquals("EUR", result.getCurrency());
    }
}
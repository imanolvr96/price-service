package com.inditex.core.priceservice.integration.infrastructure.persistence.repository;

import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import com.inditex.core.priceservice.infrastructure.persistence.repository.JpaPriceRepository;
import com.inditex.core.priceservice.infrastructure.persistence.repository.PriceRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class PriceRepositoryImplTest {

    @Autowired
    private PriceRepositoryImpl priceRepositoryImpl;

    @Autowired
    private JpaPriceRepository jpaPriceRepository;

    @Test
    void testFindApplicablePrice() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setProductId(1);
        priceEntity.setBrandId(1);
        priceEntity.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        priceEntity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        priceEntity.setPriority(1);
        priceEntity.setPrice(100.0);
        priceEntity.setCurrency("EUR");
        jpaPriceRepository.save(priceEntity);

        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 0, 0, 0);
        PriceEntity result = priceRepositoryImpl.findApplicablePrice(applicationDate, 1, 1);

        assertNotNull(result);
        assertEquals(100.0, result.getPrice());
        assertEquals("EUR", result.getCurrency());
    }
}
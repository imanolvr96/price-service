package com.inditex.core.priceservice.integration.infrastructure.persistence.repository;

import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import com.inditex.core.priceservice.infrastructure.persistence.repository.JpaPriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DataInitializerTest {

    @Autowired
    private JpaPriceRepository jpaPriceRepository;

    @Test
    void initData_shouldInsertPredefinedRecords() {
        List<PriceEntity> prices = jpaPriceRepository.findAll();
        assertEquals(4, prices.size(), "4 records should have been inserted.");
    }
}

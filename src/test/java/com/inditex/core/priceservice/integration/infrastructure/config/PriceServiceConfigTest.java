package com.inditex.core.priceservice.integration.infrastructure.config;

import com.inditex.core.priceservice.domain.repository.PriceRepository;
import com.inditex.core.priceservice.infrastructure.persistence.repository.PriceRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PriceServiceConfigTest {

    @Autowired
    @Qualifier("priceRepositoryImpl")
    private PriceRepository priceRepository;

    @Test
    void testPriceRepositoryBean() {
        assertNotNull(priceRepository, "PriceRepository bean should be available in the application context");
        assertInstanceOf(PriceRepositoryImpl.class, priceRepository, "PriceRepository should be an instance of PriceRepositoryImpl");
    }
}
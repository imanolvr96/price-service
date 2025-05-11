package com.inditex.core.priceservice.integration.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OpenApiConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testOpenApiBeanExists() {
        assertNotNull(applicationContext.getBean(OpenAPI.class), "OpenAPI bean should be present");
    }
}
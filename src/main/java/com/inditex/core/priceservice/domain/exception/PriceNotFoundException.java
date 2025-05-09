package com.inditex.core.priceservice.domain.exception;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        super(String.format("No price found for productId=%d, brandId=%d, date=%s", productId, brandId, applicationDate));
    }
}
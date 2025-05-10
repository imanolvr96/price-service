package com.inditex.core.priceservice.domain.exception;

import java.time.LocalDateTime;

/**
 * Custom exception thrown when no applicable price is found for the given product, brand, and application date.
 * <p>
 * This exception is typically thrown by the domain service layer when the price repository
 * does not return a valid result for the provided input parameters.
 * </p>
 *
 * @author Imanol Villalba Rodríguez
 * @since 2025-05-10
 */
public class PriceNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code PriceNotFoundException} with a detailed message including
     * the product ID, brand ID, and application date.
     *
     * @param productId       the ID of the product
     * @param brandId         the ID of the brand
     * @param applicationDate the date and time for which the price was requested
     */
    public PriceNotFoundException(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        super(String.format("No price found for productId=%d, brandId=%d, date=%s", productId, brandId, applicationDate));
    }
}
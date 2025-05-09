package com.inditex.core.priceservice.domain.repository;

import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;

import java.time.LocalDateTime;

/**
 * Repository interface for accessing price information.
 * <p>
 * This interface defines the contract for retrieving the applicable price
 * based on the application date, product ID, and brand ID.
 * Implementations of this interface should handle the logic to query the underlying data source.
 * </p>
 */
public interface PriceRepository {

    /**
     * Finds the applicable price for the given brand, product, and application date.
     *
     * @param applicationDate the date and time when the price should be applied
     * @param productId       the ID of the product
     * @param brandId         the ID of the brand
     * @return the applicable PriceEntity, or {@code null} if none found
     */
    PriceEntity findApplicablePrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}


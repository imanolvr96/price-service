package com.inditex.core.priceservice.application.validation;

import com.inditex.core.priceservice.domain.exception.PriceNotFoundException;
import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Validates the existence of a price for a given product, brand, and application date.
 * <p>
 * Throws a {@link PriceNotFoundException} if no applicable price is found.
 * </p>
 *
 * @author Imanol Villalba Rodríguez
 * @since 2025-05-10
 */
@Component
public class PriceValidator {

    /**
     * Checks if the given price is not null. If it is null, throws a {@link PriceNotFoundException}.
     *
     * @param price           the price entity to validate
     * @param productId       the ID of the product
     * @param brandId         the ID of the brand
     * @param applicationDate the application date for which the price is requested
     * @throws PriceNotFoundException if the price is null
     */
    public void validatePriceFound(PriceEntity price, Integer productId, Integer brandId, LocalDateTime applicationDate) {
        if (price == null) {
            throw new PriceNotFoundException(productId, brandId, applicationDate);
        }
    }
}

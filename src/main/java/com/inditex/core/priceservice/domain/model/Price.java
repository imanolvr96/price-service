package com.inditex.core.priceservice.domain.model;

import java.time.LocalDateTime;

/**
 * A record representing the price information for a product.
 * <p>
 * This record holds the details of a price entry for a specific product, brand, and date range.
 * It contains information about the price list, the start and end dates of the price's validity,
 * the product ID, the brand ID, the price amount, the priority of the price, and the currency.
 * </p>
 *
 * @param brandId   The ID of the brand associated with the price.
 * @param startDate The date and time when the price becomes valid.
 * @param endDate   The date and time when the price ceases to be valid.
 * @param priceList The ID of the price list under which the price is listed.
 * @param productId The ID of the product for which the price applies.
 * @param priority  The priority of the price, determining which price applies if there are multiple.
 * @param price     The price value for the product.
 * @param currency  The currency of the price (e.g., "EUR", "USD").
 */
public record Price(
        Integer brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priceList,
        Integer productId,
        Integer priority,
        Double price,
        String currency
) {
}
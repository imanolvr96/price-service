package com.inditex.core.priceservice.infrastructure.persistence.repository;

import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

/**
 * JPA repository for accessing the "prices" table.
 * Defines a native query to retrieve the applicable price
 * based on the application date, product ID, and brand ID.
 */
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    /**
     * Retrieves the applicable price for a given product and brand at a specific application date.
     * The method selects the price from the 'prices' table based on the provided product ID, brand ID,
     * and application date, and returns the price with the highest priority within the specified date range.
     *
     * <p>The query ensures that the price selected is within the valid date range (between 'start_date' and
     * 'end_date') and is the one with the highest priority (using 'ORDER BY priority DESC').
     * If there are multiple applicable prices, the one with the highest priority is returned.</p>
     *
     * @param applicationDate the date and time for which the price is being queried. This date must fall within
     *                        the price's start and end date range.
     * @param productId       the unique identifier of the product for which the price is being queried.
     * @param brandId         the unique identifier of the brand for which the price is being queried.
     * @return a {@link PriceEntity} representing the applicable price, or {@code null} if no price is found
     * for the given parameters.
     */
    @Query(value = """
            SELECT *
            FROM prices
            WHERE :applicationDate BETWEEN start_date AND end_date
              AND product_id = :productId
              AND brand_id = :brandId
            ORDER BY priority DESC
            LIMIT 1
            """, nativeQuery = true)
    PriceEntity findApplicablePrice(
            @Param("applicationDate") LocalDateTime applicationDate,
            @Param("productId") Integer productId,
            @Param("brandId") Integer brandId
    );
}
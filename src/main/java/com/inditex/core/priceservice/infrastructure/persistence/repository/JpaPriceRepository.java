package com.inditex.core.priceservice.infrastructure.persistence.repository;

import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

/**
 * JPA repository for accessing the "prices" table.
 * <p>
 * This interface defines the methods for interacting with the prices table,
 * specifically focusing on retrieving the applicable price based on a product ID,
 * brand ID, and application date. It uses a native SQL query to select the price
 * with the highest priority that falls within the valid date range.
 * </p>
 *
 * @author Imanol Villalba Rodríguez
 * @since 2025-05-10
 */
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    /**
     * Retrieves the applicable price for a given product, brand, and application date.
     * <p>
     * This method performs a native SQL query to select the price from the 'prices' table based on the provided
     * product ID, brand ID, and application date. The query ensures that the price is within the valid date range
     * (between 'start_date' and 'end_date'), and selects the price with the highest priority (using 'ORDER BY priority DESC').
     * If there are multiple prices applicable at the same time, the one with the highest priority will be returned.
     * </p>
     *
     * @param applicationDate the date and time when the price is being applied.
     *                        This must fall within the price's start and end date range.
     * @param productId       the unique identifier of the product.
     * @param brandId         the unique identifier of the brand.
     * @return a {@link PriceEntity} representing the applicable price,
     * or {@code null} if no price is found for the given parameters.
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
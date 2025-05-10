package com.inditex.core.priceservice.infrastructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO representing the response structure for a price query via the REST API.
 *
 * <p>This Data Transfer Object (DTO) is used to represent the structure of the response
 * when querying for a price for a given product and brand. It contains details such as
 * the product ID, brand ID, price list ID, price validity period, and the final price.
 * This class is annotated with Swagger's {@link Schema} annotations to document the API response.</p>
 *
 * @author Imanol Villalba Rodríguez
 * @date 10/05/2025
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Price information response")
public class PriceResponseDto {

    /**
     * The product identifier.
     *
     * <p>This field represents the unique identifier of the product for which the price is being retrieved.</p>
     */
    @Schema(description = "Product identifier", example = "35455")
    private Integer productId;

    /**
     * The brand identifier.
     *
     * <p>This field represents the unique identifier of the brand associated with the product.</p>
     */
    @Schema(description = "Brand identifier", example = "1")
    private Integer brandId;

    /**
     * The price list identifier (tariff).
     *
     * <p>This field represents the identifier of the price list or tariff that applies to the product.</p>
     */
    @Schema(description = "Price list identifier (tariff)", example = "2")
    private Integer priceList;

    /**
     * The start date of the price validity period.
     *
     * <p>This field indicates when the price for the product becomes valid.</p>
     */
    @Schema(description = "Start date of price validity", example = "2020-06-14T15:00:00")
    private LocalDateTime startDate;

    /**
     * The end date of the price validity period.
     *
     * <p>This field indicates when the price for the product ceases to be valid.</p>
     */
    @Schema(description = "End date of price validity", example = "2020-12-31T23:59:59")
    private LocalDateTime endDate;

    /**
     * The final price to apply for the product.
     *
     * <p>This field represents the price value that should be applied to the product for the given brand and date range.</p>
     */
    @Schema(description = "Final price to apply", example = "35.50")
    private Double price;
}
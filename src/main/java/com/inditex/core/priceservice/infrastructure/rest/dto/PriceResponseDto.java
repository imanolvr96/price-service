package com.inditex.core.priceservice.infrastructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO representing the response structure for a price query via the REST API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Price information response")
public class PriceResponseDto {

    @Schema(description = "Product identifier", example = "35455")
    private Integer productId;

    @Schema(description = "Brand identifier", example = "1")
    private Integer brandId;

    @Schema(description = "Price list identifier (tariff)", example = "2")
    private Integer priceList;

    @Schema(description = "Start date of price validity", example = "2020-06-14T15:00:00")
    private LocalDateTime startDate;

    @Schema(description = "End date of price validity", example = "2020-12-31T23:59:59")
    private LocalDateTime endDate;

    @Schema(description = "Final price to apply", example = "35.50")
    private Double price;
}
package com.inditex.core.priceservice.infrastructure.rest.controller;

import com.inditex.core.priceservice.application.service.PriceService;
import com.inditex.core.priceservice.infrastructure.rest.dto.ApiErrorResponse;
import com.inditex.core.priceservice.infrastructure.rest.dto.PriceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * REST controller that handles HTTP requests related to price information.
 *
 * <p>This controller provides an endpoint to retrieve the applicable price
 * for a product and brand at a specific date and time. It delegates the business
 * logic to the {@link PriceService} and returns structured responses.</p>
 */
@Slf4j
@Validated
@RestController
@RequestMapping("api/prices")
@Tag(name = "Price Management", description = "Operations related to price management")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    /**
     * Retrieves the applicable price for the given brand, product, and application date.
     *
     * @param brandId         the ID of the brand
     * @param productId       the ID of the product
     * @param applicationDate the date and time when the price is being applied
     * @return a PriceResponseDto containing the price details if found
     */
    @Operation(
            summary = "Get applicable price",
            description = "Retrieve the applicable price for a product based on brand and application date.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Price found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PriceResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request parameters",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Price not found for the given product, brand, and application date",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unexpected internal server error",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<PriceResponseDto> getPrice(
            @Parameter(description = "ID of the brand", required = true)
            @RequestParam @NotNull(message = "brandId is required") @PositiveOrZero(message = "brandId must be zero or positive") Integer brandId,

            @Parameter(description = "ID of the product", required = true)
            @RequestParam @NotNull(message = "productId is required") @PositiveOrZero(message = "productId must be zero or positive") Integer productId,

            @Parameter(description = "Application date and time", required = true)
            @RequestParam @NotNull(message = "applicationDate is required")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {

        log.info("Request received for brandId={}, productId={}, applicationDate={}", brandId, productId, applicationDate);

        PriceResponseDto responseDto = priceService.getPrice(applicationDate, productId, brandId);

        log.info("Returning price: {}", responseDto);
        return ResponseEntity.ok(responseDto);
    }
}
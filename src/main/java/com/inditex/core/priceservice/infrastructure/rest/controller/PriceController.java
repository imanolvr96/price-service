package com.inditex.core.priceservice.infrastructure.rest.controller;

import com.inditex.core.priceservice.application.service.PriceService;
import com.inditex.core.priceservice.infrastructure.rest.dto.PriceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Controller responsible for handling incoming API requests related to price information.
 * <p>
 * This controller exposes endpoints to retrieve price data for a given product, brand, and date range.
 * It interacts with the {@link PriceService} to fetch price information and return it in the form of DTOs.
 * </p>
 */
@Slf4j
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
                    @ApiResponse(responseCode = "200", description = "Price found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PriceResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Price not found for the given product, brand, and application date")
            }
    )
    @GetMapping("/getPrice")
    public ResponseEntity<PriceResponseDto> getPrice(
            @Parameter(description = "ID of the brand", required = true) @RequestParam Integer brandId,
            @Parameter(description = "ID of the product", required = true) @RequestParam Integer productId,
            @Parameter(description = "Application date and time", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {

        log.info("Received request to retrieve price for product ID: {}, brand ID: {} at application date: {}", productId, brandId, applicationDate);

        // Retrieve the price using the service
        Optional<PriceResponseDto> priceResponse = priceService.getPrice(applicationDate, productId, brandId);

        if (priceResponse.isEmpty()) {
            log.warn("No price found for product ID: {} with brand ID: {} at application date: {}", productId, brandId, applicationDate);
            return ResponseEntity.notFound().build();  // Return 404 if no price is found
        }

        log.info("Price found for product ID: {} with brand ID: {} at application date: {}", productId, brandId, applicationDate);
        return ResponseEntity.ok(priceResponse.get());  // Return the price in the response
    }
}
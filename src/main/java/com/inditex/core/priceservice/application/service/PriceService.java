package com.inditex.core.priceservice.application.service;

import com.inditex.core.priceservice.application.mapper.PriceMapper;
import com.inditex.core.priceservice.domain.repository.PriceRepository;
import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import com.inditex.core.priceservice.infrastructure.rest.dto.PriceResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service class responsible for retrieving price information based on product, brand, and application date.
 * This service interacts with the PriceRepository to access data from the database.
 */
@Slf4j
@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Retrieves the applicable price for the given brand, product, and application date.
     *
     * @param brandId         the ID of the brand
     * @param productId       the ID of the product
     * @param applicationDate the date and time when the price is being applied
     * @return an Optional containing a list with a single PriceResponseDto if found
     */
    public Optional<PriceResponseDto> getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        log.info("Retrieving price for product ID: {} with brand ID: {} at application date: {}", productId, brandId, applicationDate);

        PriceEntity price = priceRepository.findApplicablePrice(applicationDate, productId, brandId);

        if (price == null) {
            log.warn("No price found for product ID: {} with brand ID: {} at application date: {}", productId, brandId, applicationDate);
            return Optional.empty();
        }

        // Map the PriceEntity to a PriceResponseDto
        PriceResponseDto priceResponseDto = PriceMapper.INSTANCE.toDto(price);

        log.info("Found price for product ID: {} with brand ID: {} at application date: {}", productId, brandId, applicationDate);
        return Optional.of(priceResponseDto);
    }
}
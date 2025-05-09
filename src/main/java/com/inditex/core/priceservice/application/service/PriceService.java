package com.inditex.core.priceservice.application.service;

import com.inditex.core.priceservice.application.mapper.PriceMapper;
import com.inditex.core.priceservice.domain.exception.PriceNotFoundException;
import com.inditex.core.priceservice.domain.repository.PriceRepository;
import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import com.inditex.core.priceservice.infrastructure.rest.dto.PriceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service responsible for retrieving price information based on product, brand, and application date.
 * <p>
 * Delegates data access to the domain repository and maps domain entities to response DTOs.
 * </p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    /**
     * Retrieves the applicable price for the given brand, product, and application date.
     *
     * @param applicationDate the date and time of application
     * @param productId       the ID of the product
     * @param brandId         the ID of the brand
     * @return the applicable price as a DTO
     * @throws PriceNotFoundException if no applicable price is found
     */
    public PriceResponseDto getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        log.debug("Fetching price for productId={}, brandId={}, date={}", productId, brandId, applicationDate);

        PriceEntity price = priceRepository.findApplicablePrice(applicationDate, productId, brandId);

        if (price == null) {
            log.warn("No applicable price found for productId={}, brandId={}, date={}", productId, brandId, applicationDate);
            throw new PriceNotFoundException(productId, brandId, applicationDate);
        }

        PriceResponseDto dto = priceMapper.toDto(price);
        log.debug("Mapped price entity to DTO: {}", dto);
        return dto;
    }
}
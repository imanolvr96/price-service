package com.inditex.core.priceservice.infrastructure.persistence.repository;

import com.inditex.core.priceservice.domain.repository.PriceRepository;
import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Adapter that implements the domain's {@link PriceRepository} interface
 * using a JPA-based repository to access the data source.
 */
@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    private final JpaPriceRepository jpaPriceRepository;

    /**
     * Retrieves the most applicable price for the specified brand, product, and date.
     *
     * @param applicationDate the date for which the price is requested
     * @param productId       the ID of the product
     * @param brandId         the ID of the brand
     * @return the applicable {@link PriceEntity}, or null if none is found
     */
    @Override
    @Transactional(readOnly = true)
    public PriceEntity findApplicablePrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return jpaPriceRepository.findApplicablePrice(applicationDate, productId, brandId);
    }
}
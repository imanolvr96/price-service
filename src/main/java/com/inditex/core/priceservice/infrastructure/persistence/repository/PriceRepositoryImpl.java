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
 * <p>
 * This class provides the implementation of the {@link PriceRepository} interface, using a JPA-based repository
 * to interact with the underlying data source. It delegates the actual data retrieval to the {@link JpaPriceRepository}
 * and is responsible for fetching the most applicable price for a given brand, product, and application date.
 * </p>
 *
 * @author Imanol Villalba Rodríguez
 * @date 10/05/2025
 */
@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    private final JpaPriceRepository jpaPriceRepository;

    /**
     * Retrieves the most applicable price for the specified brand, product, and date.
     * <p>
     * This method delegates the request to {@link JpaPriceRepository} to find the price that is valid for the
     * given application date, product ID, and brand ID. The query ensures that the price falls within the valid date
     * range and prioritizes the most relevant price based on the specified parameters.
     * </p>
     *
     * @param applicationDate the date for which the price is requested
     * @param productId       the ID of the product
     * @param brandId         the ID of the brand
     * @return the applicable {@link PriceEntity}, or null if no price is found for the given parameters
     */
    @Override
    @Transactional(readOnly = true)
    public PriceEntity findApplicablePrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return jpaPriceRepository.findApplicablePrice(applicationDate, productId, brandId);
    }
}
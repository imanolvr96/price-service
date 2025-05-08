package com.inditex.core.priceservice.infrastructure.persistence.repository;

import com.inditex.core.priceservice.domain.repository.PriceRepository;
import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Repository
public class PriceRepositoryImpl implements PriceRepository {

    private final JpaPriceRepository jpaPriceRepository;

    public PriceRepositoryImpl(JpaPriceRepository jpaPriceRepository) {
        this.jpaPriceRepository = jpaPriceRepository;
    }

    @Override
    @Transactional
    public PriceEntity findApplicablePrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return jpaPriceRepository.findApplicablePrice(applicationDate, productId, brandId);
    }

}

package com.inditex.core.priceservice.domain.repository;


import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;

import java.time.LocalDateTime;


public interface PriceRepository {

    PriceEntity findApplicablePrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}


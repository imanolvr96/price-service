package com.inditex.core.priceservice.infrastructure.repository;

import com.inditex.core.priceservice.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Integer> {
}
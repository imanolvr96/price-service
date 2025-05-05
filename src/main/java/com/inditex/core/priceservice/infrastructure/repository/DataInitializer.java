package com.inditex.core.priceservice.infrastructure.repository;

import com.inditex.core.priceservice.domain.model.Price;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final PriceRepository priceRepository;

    @PostConstruct
    public void initData() {
        priceRepository.save(new Price(
                null,
                1,
                35455,
                0,
                35.50,
                "EUR",
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1
        ));

        priceRepository.save(new Price(
                null,
                1,
                35455,
                1,
                25.45,
                "EUR",
                LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30, 0),
                2
        ));

        priceRepository.save(new Price(
                null,
                1,
                35455,
                1,
                30.50,
                "EUR",
                LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                LocalDateTime.of(2020, 6, 15, 11, 0, 0),
                3
        ));

        priceRepository.save(new Price(
                null,
                1,
                35455,
                1,
                38.95,
                "EUR",
                LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                4
        ));
    }
}
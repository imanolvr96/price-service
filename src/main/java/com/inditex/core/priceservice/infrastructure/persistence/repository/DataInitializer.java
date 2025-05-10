package com.inditex.core.priceservice.infrastructure.persistence.repository;

import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Component responsible for initializing data in the price repository.
 * <p>
 * This class is responsible for populating the database with sample price data upon application startup.
 * The data is inserted into the {@link JpaPriceRepository} using {@link PriceEntity} objects, which are saved
 * into the database. This ensures that the database is pre-populated with test data for the application to work with.
 * </p>
 *
 * @author Imanol Villalba Rodríguez
 * @since 2025-05-10
 */
@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final JpaPriceRepository jpaPriceRepository;

    /**
     * Initializes the price data by saving predefined {@link PriceEntity} objects into the repository.
     * <p>
     * This method is annotated with {@link PostConstruct} and is executed automatically after the
     * {@link DataInitializer} bean is created and dependencies are injected. It saves a series of predefined
     * {@link PriceEntity} objects into the repository, simulating a set of price records for testing and development purposes.
     * </p>
     */
    @PostConstruct
    public void initData() {
        jpaPriceRepository.save(new PriceEntity(
                null,
                1,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1,
                35455,
                0,
                35.50,
                "EUR"
        ));

        jpaPriceRepository.save(new PriceEntity(
                null,
                1,
                LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30, 0),
                2,
                35455,
                1,
                25.45,
                "EUR"
        ));

        jpaPriceRepository.save(new PriceEntity(
                null,
                1,
                LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                LocalDateTime.of(2020, 6, 15, 11, 0, 0),
                3,
                35455,
                1,
                30.50,
                "EUR"
        ));

        jpaPriceRepository.save(new PriceEntity(
                null,
                1,
                LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                4,
                35455,
                1,
                38.95,
                "EUR"
        ));
    }
}
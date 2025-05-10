package com.inditex.core.priceservice.infrastructure.config;

import com.inditex.core.priceservice.domain.repository.PriceRepository;
import com.inditex.core.priceservice.infrastructure.persistence.repository.JpaPriceRepository;
import com.inditex.core.priceservice.infrastructure.persistence.repository.PriceRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up beans related to the Price Service.
 * <p>
 * This configuration class defines beans for the price-related components, specifically for the
 * repository used to manage price data. It ensures that the necessary dependencies for the
 * {@link PriceRepository} are correctly instantiated and injected into the application context.
 * </p>
 *
 * @author Imanol Villalba Rodríguez
 * @since 2025-05-10
 */
@Configuration
public class PriceServiceConfig {

    /**
     * Creates a {@link PriceRepository} bean that is backed by the {@link JpaPriceRepository}.
     * <p>
     * This method defines the bean responsible for managing price data. It uses the {@link JpaPriceRepository}
     * as the data access layer, wrapping it in an implementation of {@link PriceRepository}.
     * The {@link PriceRepositoryImpl} provides the interface implementation required by the service layer.
     * </p>
     *
     * @param jpaPriceRepository The {@link JpaPriceRepository} bean, which handles the actual database interaction.
     * @return A {@link PriceRepository} bean that interacts with the database through {@link JpaPriceRepository}.
     */
    @Bean
    public PriceRepository priceRepository(JpaPriceRepository jpaPriceRepository) {
        return new PriceRepositoryImpl(jpaPriceRepository);
    }
}
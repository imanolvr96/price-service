package com.inditex.core.priceservice.application.mapper;

import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import com.inditex.core.priceservice.infrastructure.rest.dto.PriceResponseDto;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting {@link PriceEntity} objects into {@link PriceResponseDto} objects.
 * <p>
 * This mapper is implemented automatically by MapStruct and registered as a Spring bean.
 * </p>
 *
 * @author Imanol Villalba Rodríguez
 * @since 2025-05-10
 */
@Mapper(componentModel = "spring")
public interface PriceMapper {

    /**
     * Converts a {@link PriceEntity} to a {@link PriceResponseDto}.
     *
     * @param priceEntity the entity to convert
     * @return the corresponding DTO with price information
     */
    PriceResponseDto toDto(PriceEntity priceEntity);
}
package com.inditex.core.priceservice.application.mapper;

import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import com.inditex.core.priceservice.infrastructure.rest.dto.PriceResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface to convert PriceEntity to PriceResponseDto.
 */
@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    /**
     * Convert a PriceEntity to a PriceResponseDto.
     *
     * @param priceEntity the entity to convert
     * @return the corresponding PriceResponseDto
     */
    PriceResponseDto toDto(PriceEntity priceEntity);
}
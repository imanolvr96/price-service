package com.inditex.core.priceservice.unit.application.mapper;

import com.inditex.core.priceservice.application.mapper.PriceMapper;
import com.inditex.core.priceservice.infrastructure.persistence.entity.PriceEntity;
import com.inditex.core.priceservice.infrastructure.rest.dto.PriceResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PriceMapperTest {
    private final PriceMapper priceMapper = Mappers.getMapper(PriceMapper.class);

    @Test
    void testToDto() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setPrice(100.0);
        PriceResponseDto priceResponseDto = priceMapper.toDto(priceEntity);
        assertEquals(priceEntity.getPrice(), priceResponseDto.getPrice());
    }
}
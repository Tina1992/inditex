package com.ecommerce.inditex.infrastructure.rest.adapter;

import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.infrastructure.rest.dto.PriceResponseDto;
import org.mapstruct.Mapper;

/**
 * Price response adapter.
 */
@Mapper(componentModel = "spring")
public interface PriceResponseAdapter {

    /**
     * Convert the {@link PriceBO} to a {@link PriceResponseDto}
     * @param priceBO the price business object
     * @return the {@link PriceResponseDto}
     */
    PriceResponseDto fromPriceBO(PriceBO priceBO);

}

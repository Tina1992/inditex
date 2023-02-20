package com.ecommerce.inditex.infrastructure.rest.adapter;

import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.infrastructure.rest.dto.PriceResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceBOMapper {

    PriceResponseDto fromPriceBO(PriceBO priceBO);

}

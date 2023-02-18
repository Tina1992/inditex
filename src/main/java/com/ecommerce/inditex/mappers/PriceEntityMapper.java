package com.ecommerce.inditex.mappers;

import com.ecommerce.inditex.entities.PriceEntity;
import com.ecommerce.inditex.entities.PriceResponseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    PriceResponseEntity fromPriceEntity(PriceEntity priceEntity);

}

package com.ecommerce.inditex.infrastructure.database.adapter;

import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.infrastructure.database.entities.H2PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface H2PriceEntityMapper {

    PriceBO fromH2PriceEntity(final H2PriceEntity h2PriceEntity);
}

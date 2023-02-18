package com.ecommerce.inditex.database;

import com.ecommerce.inditex.entities.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface H2PriceEntityMapper {

    PriceEntity fromH2PriceEntity(final H2PriceEntity h2PriceEntity);
}

package com.ecommerce.inditex.infrastructure.database.adapter;

import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.infrastructure.database.entities.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Price entity adapter.
 */
@Mapper(componentModel = "spring")
public interface PriceEntityAdapter {

    /**
     * Convert {@link PriceEntity} to {@link PriceBO}.
     * @param priceEntity the {@link PriceEntity}
     * @return the {@link PriceBO}
     */
    @Mapping(source = "priceValue", target = "value")
    @Mapping(source = "priceCurrency", target = "currency")
    PriceBO fromPriceEntity(PriceEntity priceEntity);
}

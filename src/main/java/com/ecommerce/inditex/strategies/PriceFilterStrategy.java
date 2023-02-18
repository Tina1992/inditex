package com.ecommerce.inditex.strategies;

import com.ecommerce.inditex.entities.PriceEntity;
import com.ecommerce.inditex.exceptions.NoResultFoundException;

import java.util.List;

public interface PriceFilterStrategy {

    PriceEntity filter(List<PriceEntity> priceEntityList);

}

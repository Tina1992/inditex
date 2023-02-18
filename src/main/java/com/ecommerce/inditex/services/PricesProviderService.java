package com.ecommerce.inditex.services;

import com.ecommerce.inditex.entities.PriceEntity;
import com.ecommerce.inditex.entities.PriceFilterEntity;

import java.util.List;

public interface PricesProviderService {
    List<PriceEntity> getPrices(PriceFilterEntity priceFilterEntity);
}

package com.ecommerce.inditex.services;

import com.ecommerce.inditex.entities.PriceEntity;
import com.ecommerce.inditex.entities.PriceFilterEntity;
import com.ecommerce.inditex.entities.PriceResponseEntity;
import com.ecommerce.inditex.exceptions.NoResultFoundException;
import com.ecommerce.inditex.mappers.PriceEntityMapper;
import com.ecommerce.inditex.strategies.PriceFilterStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterPricesService {

    private PricesProviderService pricesProvider;

    private PriceFilterStrategy priceFilterStrategy;

    private PriceEntityMapper priceEntityMapper;

    public PriceResponseEntity filterPrices(PriceFilterEntity priceFilterEntity) throws NoResultFoundException {
        List<PriceEntity> priceEntityList = pricesProvider.getPrices(priceFilterEntity);
        PriceEntity priceEntity = priceFilterStrategy.filter(priceEntityList);
        return priceEntityMapper.fromPriceEntity(priceEntity);
    }

}

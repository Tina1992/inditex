package com.ecommerce.inditex.strategies;

import com.ecommerce.inditex.entities.PriceEntity;
import com.ecommerce.inditex.exceptions.NoResultFoundException;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Component
public class MaxPriorityPriceFilterStrategy implements PriceFilterStrategy{
    @Override
    public PriceEntity filter(List<PriceEntity> priceEntityList) {
        return priceEntityList.stream()
                .filter(priceEntity -> Objects.nonNull(priceEntity.getPriority()))
                .max(Comparator.comparing(PriceEntity::getPriority))
                .orElseThrow(() ->new NoResultFoundException());
    }
}

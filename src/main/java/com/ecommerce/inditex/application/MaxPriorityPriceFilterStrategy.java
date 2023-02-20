package com.ecommerce.inditex.application;

import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.application.exceptions.NoResultFoundException;
import com.ecommerce.inditex.application.ports.PriceFilterStrategyPort;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Component
public class MaxPriorityPriceFilterStrategy implements PriceFilterStrategyPort {
    @Override
    public PriceBO filter(List<PriceBO> priceBOList) {
        return priceBOList.stream()
                .filter(priceEntity -> Objects.nonNull(priceEntity.getPriority()))
                .max(Comparator.comparing(PriceBO::getPriority))
                .orElseThrow(() ->new NoResultFoundException());
    }
}

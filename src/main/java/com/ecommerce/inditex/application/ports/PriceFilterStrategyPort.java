package com.ecommerce.inditex.application.ports;

import com.ecommerce.inditex.domain.PriceBO;

import java.util.List;

/**
 * Price filter strategy port.
 */
public interface PriceFilterStrategyPort {

    /**
     * Filter prices by condition.
     * @param priceBOList prices list
     * @return the {@link PriceBO}
     */
    PriceBO filter(List<PriceBO> priceBOList);

}

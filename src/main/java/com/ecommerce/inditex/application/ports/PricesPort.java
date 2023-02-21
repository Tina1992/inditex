package com.ecommerce.inditex.application.ports;

import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.domain.PriceFilterBO;

import java.util.List;

/**
 * Prices port.
 */
public interface PricesPort {

    /**
     * Get prices by filter
     * @param priceFilterBO price filter
     * @return the list of {@link PriceBO}
     */
    List<PriceBO> getPrices(PriceFilterBO priceFilterBO);
}

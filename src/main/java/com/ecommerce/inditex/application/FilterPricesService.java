package com.ecommerce.inditex.application;

import com.ecommerce.inditex.application.ports.PricesPort;
import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.domain.PriceFilterBO;
import com.ecommerce.inditex.application.ports.PriceFilterStrategyPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Filter prices service.
 */
@Service
public class FilterPricesService {

    /**
     * Prices provider.
     */
    @Autowired
    private PricesPort pricesProvider;

    /**
     * Price filter strategy.
     */
    @Autowired
    private PriceFilterStrategyPort priceFilterStrategyPort;

    /**
     * Get price by brand id, product id and applied time.
     * @param brandId brand id
     * @param productId product id
     * @param appliedTime applied time
     * @return the price applied
     */
    public PriceBO getPrice(final Integer brandId, final Long productId, final LocalDateTime appliedTime) {
        List<PriceBO> priceBOList = pricesProvider.getPrices(PriceFilterBO.builder().appliedDate(appliedTime).productId(productId).brandId(brandId).build());
        return priceFilterStrategyPort.filter(priceBOList);
    }

}

package com.ecommerce.inditex.application.ports;

import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.domain.PriceFilterBO;

import java.util.List;

public interface PricesPort {
    List<PriceBO> getPrices(PriceFilterBO priceFilterBO);
}

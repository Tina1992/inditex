package com.ecommerce.inditex.application.ports;

import com.ecommerce.inditex.domain.PriceBO;

import java.util.List;

public interface PriceFilterStrategyPort {

    PriceBO filter(List<PriceBO> priceBOList);

}

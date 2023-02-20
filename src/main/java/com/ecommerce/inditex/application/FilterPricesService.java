package com.ecommerce.inditex.application;

import com.ecommerce.inditex.application.ports.PricesPort;
import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.domain.PriceFilterBO;
import com.ecommerce.inditex.application.ports.PriceFilterStrategyPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FilterPricesService {

    @Autowired
    private PricesPort pricesProvider;

    @Autowired
    private PriceFilterStrategyPort priceFilterStrategyPort;

    public PriceBO filterPrices(final String brandId,  final String productId, final LocalDateTime appliedTime) {
        List<PriceBO> priceBOList = pricesProvider.getPrices(PriceFilterBO.builder().appliedDate(appliedTime).productId(productId).brandId(brandId).build());
        return priceFilterStrategyPort.filter(priceBOList);
    }

}

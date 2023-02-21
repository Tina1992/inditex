package com.ecommerce.inditex.infrastructure.database;

import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.domain.PriceFilterBO;
import com.ecommerce.inditex.application.ports.PricesPort;
import com.ecommerce.inditex.infrastructure.database.adapter.PriceEntityAdapter;
import com.ecommerce.inditex.infrastructure.database.repositories.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Prices port implementation.
 */
@Service
public class PricesPortImpl implements PricesPort {

    /**
     * Prices repository.
     */
    @Autowired
    private PricesRepository pricesRepository;

    /**
     * Price entity adapter
     */
    @Autowired
    private PriceEntityAdapter priceEntityAdapter;

    /**
     * Get prices from database considering the filter.
     * @param priceFilterBO price filter
     * @return the list of {@link PriceBO}
     */
    @Override
    public List<PriceBO> getPrices(final PriceFilterBO priceFilterBO) {
        return pricesRepository.findApplicablePrices(Integer.valueOf(priceFilterBO.getBrandId()), Long.valueOf(priceFilterBO.getProductId()), priceFilterBO.getAppliedDate())
                .stream()
                .map(entity -> priceEntityAdapter.fromPriceEntity(entity))
                .collect(Collectors.toList());
    }
}

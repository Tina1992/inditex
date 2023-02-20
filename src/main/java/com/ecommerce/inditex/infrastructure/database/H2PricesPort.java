package com.ecommerce.inditex.infrastructure.database;

import com.ecommerce.inditex.domain.PriceBO;
import com.ecommerce.inditex.domain.PriceFilterBO;
import com.ecommerce.inditex.application.ports.PricesPort;
import com.ecommerce.inditex.infrastructure.database.adapter.H2PriceEntityMapper;
import com.ecommerce.inditex.infrastructure.database.repositories.H2PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class H2PricesPort implements PricesPort {

    @Autowired
    private H2PricesRepository h2PricesRepository;

    @Autowired
    private H2PriceEntityMapper h2PriceEntityMapper;

    @Override
    public List<PriceBO> getPrices(PriceFilterBO priceFilterBO) {
        return h2PricesRepository.findApplicablePrices(Long.valueOf(priceFilterBO.getProductId()), Integer.valueOf(priceFilterBO.getBrandId()), priceFilterBO.getAppliedDate())
                .stream()
                .map(h2entity -> h2PriceEntityMapper.fromH2PriceEntity(h2entity))
                .collect(Collectors.toList());
    }
}

package com.ecommerce.inditex.database;

import com.ecommerce.inditex.entities.PriceEntity;
import com.ecommerce.inditex.entities.PriceFilterEntity;
import com.ecommerce.inditex.services.PricesProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class H2PricesProviderService implements PricesProviderService {

    @Autowired
    private H2PricesRepository h2PricesRepository;

    @Autowired
    private H2PriceEntityMapper h2PriceEntityMapper;

    @Override
    public List<PriceEntity> getPrices(PriceFilterEntity priceFilterEntity) {
        return h2PricesRepository.findApplicablePrices(Long.valueOf(priceFilterEntity.getProductId()), Integer.valueOf(priceFilterEntity.getBrandId()), priceFilterEntity.getAppliedDate())
                .stream()
                .map(h2entity -> h2PriceEntityMapper.fromH2PriceEntity(h2entity))
                .collect(Collectors.toList());
    }
}

package com.ecommerce.inditex;

import com.ecommerce.inditex.entities.PriceFilterEntity;
import com.ecommerce.inditex.entities.PriceResponseEntity;
import com.ecommerce.inditex.services.FilterPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@EnableJpaRepositories
public class PricesController {

    @Autowired
    private FilterPricesService filterPricesService;

    @GetMapping("/brands/{brand_id}/products/{product_id}")
    public PriceResponseEntity getPrices(@PathVariable("brand_id") String brandId,
                                         @PathVariable("product_id") String productId,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time) {
        PriceFilterEntity priceFilterEntity = PriceFilterEntity.builder().brandId(brandId).productId(productId).appliedDate(time).build();
        return filterPricesService.filterPrices(priceFilterEntity);
    }
}


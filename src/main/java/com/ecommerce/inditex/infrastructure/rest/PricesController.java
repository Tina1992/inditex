package com.ecommerce.inditex.infrastructure.rest;

import com.ecommerce.inditex.infrastructure.rest.adapter.PriceBOMapper;
import com.ecommerce.inditex.infrastructure.rest.dto.PriceResponseDto;
import com.ecommerce.inditex.application.FilterPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PricesController {

    @Autowired
    private FilterPricesService filterPricesService;

    @Autowired
    private PriceBOMapper priceBOMapper;

    @GetMapping("/brands/{brand_id}/products/{product_id}")
    public PriceResponseDto getPrices(@PathVariable("brand_id") String brandId,
                                      @PathVariable("product_id") String productId,
                                      @RequestParam(name = "applied_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appliedTime) {
        return priceBOMapper.fromPriceBO(filterPricesService.filterPrices(brandId, productId, appliedTime));
    }
}


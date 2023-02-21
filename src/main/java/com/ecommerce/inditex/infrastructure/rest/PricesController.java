package com.ecommerce.inditex.infrastructure.rest;

import com.ecommerce.inditex.infrastructure.rest.adapter.PriceResponseAdapter;
import com.ecommerce.inditex.infrastructure.rest.dto.PriceResponseDto;
import com.ecommerce.inditex.application.FilterPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.ecommerce.inditex.infrastructure.rest.utils.RestConstants.*;

/**
 * Prices controller.
 */
@RestController
public class PricesController {

    /**
     * Filter prices service.
     */
    @Autowired
    private FilterPricesService filterPricesService;

    /**
     * Price response adapter.
     */
    @Autowired
    private PriceResponseAdapter priceResponseAdapter;

    /**
     * Get price applicable at time for the given brand and product.
     *
     * @param brandId brand id
     * @param productId product id
     * @param appliedTime applied time
     * @return the {@link PriceResponseDto}
     */
    @GetMapping("/brands/{brand_id}/products/{product_id}")
    public PriceResponseDto getPrice(final @PathVariable(BRAND_ID_PARAMETER_NAME) Integer brandId,
                                     final @PathVariable(PRODUCT_ID_PARAMETER_NAME) Long productId,
                                     final @RequestParam(name = APPLIED_TIME_PARAMETER_NAME) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appliedTime) {
        return priceResponseAdapter.fromPriceBO(filterPricesService.getPrice(brandId, productId, appliedTime));
    }
}


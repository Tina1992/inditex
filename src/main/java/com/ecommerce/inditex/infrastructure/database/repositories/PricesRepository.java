package com.ecommerce.inditex.infrastructure.database.repositories;

import com.ecommerce.inditex.infrastructure.database.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Prices repository.
 */
@Repository
public interface PricesRepository extends JpaRepository<PriceEntity, Integer> {

    /**
     * Find applicable prices at time for a specified product and brand.
     * @param brandId brand id
     * @param productId product id
     * @param atTime time
     * @return the list of {@link PriceEntity}
     */
    @Query("SELECT p from PriceEntity p where brandEntity.id = :brandId "
            + "and productId = :productId "
            + "and startDate <= :currentDate "
            + "and :currentDate <= endDate")
    List<PriceEntity> findApplicablePrices(@Param("brandId") Integer brandId,
                                           @Param("productId") Long productId,
                                           @Param("currentDate") LocalDateTime atTime);
}

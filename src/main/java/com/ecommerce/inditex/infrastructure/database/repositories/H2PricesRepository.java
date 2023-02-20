package com.ecommerce.inditex.infrastructure.database.repositories;

import com.ecommerce.inditex.infrastructure.database.entities.H2PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface H2PricesRepository extends JpaRepository<H2PriceEntity, Integer> {

    @Query("SELECT p from H2PriceEntity p where productId = :productId " +
            "and brandId = :brandId " +
            "and startDate <= :currentDate " +
            "and :currentDate <= endDate")
    List<H2PriceEntity> findApplicablePrices(final @Param("productId") Long productId,
                                          final @Param("brandId") Integer brandId,
                                          final @Param("currentDate") LocalDateTime localDateTime);
}

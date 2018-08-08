package com.gxh.apserver.repository.interfaces;

import com.gxh.apserver.entity.PromotionLevelRateCard;
import com.gxh.apserver.entity.PromotionLevelSKU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PromotionLevelSKURepository extends JpaRepository<PromotionLevelSKU,Long> {
    @Query("select psku from PromotionLevelSKU psku where psku.promo = ?1")
    Optional<List<PromotionLevelSKU>> findAllByPromoID(Long promoID);
}

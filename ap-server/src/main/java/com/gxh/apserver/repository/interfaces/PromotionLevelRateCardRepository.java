package com.gxh.apserver.repository.interfaces;

import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.PromotionLevelRateCard;
import com.gxh.apserver.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PromotionLevelRateCardRepository extends JpaRepository<PromotionLevelRateCard,Long> {
    @Query("select prt from PromotionLevelRateCard prt where prt.promo = ?1")
    Optional<List<PromotionLevelRateCard>> findAllByPromoID(Long promoID);
    
    @Query("select prt from PromotionLevelRateCard prt where prt.promo = ?1 and prt.annualpromo = ?2")
    Optional<List<PromotionLevelRateCard>> findAllByPromoAndAnnualPromoID(Long promoID,Long annualPromoID);
}

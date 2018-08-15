package com.gxh.apserver.repository.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gxh.apserver.entity.PromotionLevelSKU;

public interface PromotionLevelSKURepository extends JpaRepository<PromotionLevelSKU,Long> {
    @Query("select psku from PromotionLevelSKU psku where psku.promo = ?1")
    Optional<List<PromotionLevelSKU>> findAllByPromoID(Long promoID);
    
    @Modifying
	@Query(" delete from PromotionLevelSKU p where p.promo = ?3 and p.rateCard = ?2 "
			+ "and p.dualMailer = ?1 and p.product = ?4 and p.promoCount = ?5 ") 
    public void deleteByRowData(Long dmId, Long rcId, Long promoId, Long productId, int prmoCount);
}

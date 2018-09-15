package com.gxh.apserver.repository.interfaces;

import com.gxh.apserver.constants.AnnualPromotionStatus;
import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion,Long> {

    @Query("select p from Promotion p where p.createdByUser = ?1")
    Optional<Promotion> findPromotionByUser(User user);

    @Query("select p from Promotion p where p.status = ?1")
    Optional<List<Promotion>> findAllPromotionByStatus(PromotionStatus status);
    
    @Query("select p from Promotion p, AnnualPromotion ap  where ap.promo=p.id and  "
    		+ "p.status = ?1 and ( ap.status = ?2 or ap.status = ?3 )")
    Optional<List<Promotion>> findAllByAnnualPromotionStatuses(PromotionStatus status,
    		AnnualPromotionStatus draftStatus, AnnualPromotionStatus rejectedStatus);

}

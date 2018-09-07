package com.gxh.apserver.repository.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.entity.AnnualPromotion;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.Supplier;

public interface AnnualPromotionRepository extends JpaRepository<AnnualPromotion,Long> {

    @Query("select p from AnnualPromotion p where p.supplier = ?1 and p.status = ?2")
    Optional<AnnualPromotion> findSupplierPromotionByStatus(Supplier supplier,PromotionStatus status);

    @Query("select p from AnnualPromotion p where p.supplier = ?1")
    Optional<List<AnnualPromotion>> findAllPromotionBySupplierID(Supplier supplier);

    @Query("select p from AnnualPromotion p where p.supplier = ?1 and p.createdAt = ?2")
    Optional<AnnualPromotion> findSupplierPromotionByYear(Supplier supplier,Date date);

    @Query("select p from AnnualPromotion p where p.supplier = ?1 and p.createdAt = ?2 and p.status = ?3")
    Optional<AnnualPromotion> findSupplierPromotionByYearAndStatus(Supplier supplier,Date date,PromotionStatus status);

    @Query("update AnnualPromotion p set p.status = ?3 where p.supplier = ?1 and p.createdAt = ?2")
    AnnualPromotion updatePromotionStatus(Supplier supplier,Date date,PromotionStatus status);
    
    @Query("select ap from AnnualPromotion ap where ap.supplier = ?1 and ap.promo=?2")
    Optional<AnnualPromotion> findPromotionBySupplierAndPromo(Supplier supplier,Promotion promo);

}

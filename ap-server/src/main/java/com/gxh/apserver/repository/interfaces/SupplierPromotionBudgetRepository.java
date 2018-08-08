package com.gxh.apserver.repository.interfaces;

import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.SupplierPromotionBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SupplierPromotionBudgetRepository extends JpaRepository<SupplierPromotionBudget,Long> {
    @Query("select budget from SupplierPromotionBudget budget where budget.promotion = ?1")
    Optional<SupplierPromotionBudget> findByPromoID(Promotion promo);
}

package com.gxh.apserver.repository.interfaces;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.Supplier;
import com.gxh.apserver.entity.SupplierPromotionBudget;

public interface SupplierPromotionBudgetRepository extends JpaRepository<SupplierPromotionBudget,Long> {
    @Query("select budget from SupplierPromotionBudget budget where budget.promotion = ?1")
    Optional<SupplierPromotionBudget> findByPromoID(Promotion promo);
    @Query("select budget from SupplierPromotionBudget budget where budget.supplier = ?1")
    Optional<SupplierPromotionBudget> findBySupplierID(Supplier promo);
	
    SupplierPromotionBudget findByPromotion(Promotion promotion);
    
    @Transactional
    @Modifying
    @Query("update SupplierPromotionBudget budget set budget.budgetAllocated=?3 where budget.promotion=?1 and budget.supplier=?2")
	void updateBudget(Promotion promotion, Supplier supplier, Long budgetAllocated);
}

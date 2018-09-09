package com.gxh.apserver.util;

import com.gxh.apserver.dto.BudgetDTO;
import com.gxh.apserver.entity.SupplierPromotionBudget;

public class BudgetCalculator {
    public static BudgetDTO   getBudget(SupplierPromotionBudget promotionBudget) {
        BudgetDTO budgetDTO = new BudgetDTO();

        budgetDTO.setAllocated(promotionBudget.getBudgetAllocated());
        budgetDTO.setUsed(promotionBudget.getTotalBudget());
        Long diff = promotionBudget.getBudgetAllocated() - promotionBudget.getTotalBudget();
        budgetDTO.setDiff(diff);

        return budgetDTO;
    }
}

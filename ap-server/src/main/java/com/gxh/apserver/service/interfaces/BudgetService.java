package com.gxh.apserver.service.interfaces;

import java.text.ParseException;
import java.util.List;

import com.gxh.apserver.constants.AnnualPromotionStatus;
import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.AdminPromoDTO;
import com.gxh.apserver.dto.BudgetDTO;
import com.gxh.apserver.dto.PromoBudgetDTO;

public interface BudgetService {
	public void saveBudgetForPromotion(BudgetDTO budgetDto) throws ParseException;

	public BudgetDTO getBudgetForPromotion(Long promotionId) throws ParseException;

	public List<AdminPromoDTO> getPromosbyStatus(PromotionStatus status) throws ParseException;

	public List<PromoBudgetDTO> getAllBudgets(Long supplierId) throws ParseException;
}

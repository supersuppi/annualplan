package com.gxh.apserver.service.interfaces;

import java.text.ParseException;

import com.gxh.apserver.dto.BudgetDTO;

public interface BudgetService {

	public void saveBudgetForPromotion(BudgetDTO budgetDto) throws ParseException;
	
	public BudgetDTO getBudgetForPromotion(Long promotionId) throws ParseException;
	
}

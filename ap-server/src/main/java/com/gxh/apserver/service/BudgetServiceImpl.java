package com.gxh.apserver.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxh.apserver.dto.BudgetDTO;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.Supplier;
import com.gxh.apserver.entity.SupplierPromotionBudget;
import com.gxh.apserver.repository.interfaces.PromotionRepository;
import com.gxh.apserver.repository.interfaces.SupplierPromotionBudgetRepository;
import com.gxh.apserver.repository.interfaces.SupplierRepository;
import com.gxh.apserver.service.interfaces.BudgetService;

@Service
public class BudgetServiceImpl implements BudgetService{

	@Autowired
	private SupplierPromotionBudgetRepository budgetRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	/**
	 * Save the budget for each active promotion, if a budget exist 
	 * update the budget amount.
	 */
	@Override
	public void saveBudgetForPromotion(BudgetDTO budgetDto) throws ParseException {
		
		SupplierPromotionBudget promotionBudget = new SupplierPromotionBudget();
		Supplier supplier = supplierRepository.findById(budgetDto.getSupplierId()).get();
		Promotion promotion = promotionRepository.findById(budgetDto.getPromotionId()).get();
		
		supplier.setId(budgetDto.getSupplierId());
		promotion.setId(budgetDto.getPromotionId());
		
		promotionBudget.setPromotion(promotion);
		promotionBudget.setSupplier(supplier);
		promotionBudget.setBudgetAllocated(budgetDto.getAllocated());
		promotionBudget.setTotalBudget(0L);
		
		SupplierPromotionBudget savedPromoBudget = budgetRepository.findByPromotion(promotion);
		
		if (savedPromoBudget == null ) {
			budgetRepository.save(promotionBudget);
		} else {
			budgetRepository.updateBudget(promotionBudget.getPromotion(), promotionBudget.getSupplier(), promotionBudget.getBudgetAllocated());
		}
		
	}

}

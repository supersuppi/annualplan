package com.gxh.apserver.service;

import java.text.ParseException;
import java.util.Optional;

import javax.transaction.Transactional;

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
	@Transactional
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
		
		Optional<SupplierPromotionBudget> savedPromoBudget = budgetRepository.findByPromotion(promotion);
		
		if ( !savedPromoBudget.isPresent() ) {
			budgetRepository.save(promotionBudget);
		} else {
			budgetRepository.updateBudget(promotionBudget.getPromotion(), promotionBudget.getSupplier(), promotionBudget.getBudgetAllocated());
		}
		
	}

	@Override
	public BudgetDTO getBudgetForPromotion(Long promotionId) throws ParseException {

		Optional<Promotion> promotion = promotionRepository.findById(promotionId);
		Optional<SupplierPromotionBudget> budget = budgetRepository.findByPromotion(promotion.get());
		BudgetDTO budgetDTO = new BudgetDTO();
		
		if (budget.isPresent()) {
			budgetDTO.setAllocated(budget.get().getBudgetAllocated());
		} else {
			budgetDTO.setAllocated(0L);
		}
		
		return budgetDTO;
	}

}

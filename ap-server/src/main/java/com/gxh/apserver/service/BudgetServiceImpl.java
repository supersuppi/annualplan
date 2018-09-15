package com.gxh.apserver.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxh.apserver.constants.AnnualPromotionStatus;
import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.AdminPromoDTO;
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

	/**
	 * Get the budget for the promotions selected in the dropdown,
	 * while allocating budget.
	 */
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
	
	/*
	 * Get all the annual promotions that are either in draft or rejected status ,
	 * to allow the supplier to provide a budget amount 
	 */
	 @Override
	 public List<AdminPromoDTO> getPromosbyStatus(PromotionStatus status) throws ParseException {
		Optional<List<Promotion>> promoList = promotionRepository.findAllPromotionByStatus(status);
        List<AdminPromoDTO> promodtoList = new ArrayList<>();
        
        if(promoList.isPresent()) {
            promoList.get().forEach((promotion -> {
                AdminPromoDTO pdto = new AdminPromoDTO();
                pdto.setName(promotion.getName());
                pdto.setPid(promotion.getId());
                pdto.setPstatus(promotion.getStatus().toString());

                promodtoList.add(pdto);
            }));
        }
        
        return promodtoList;
    }

}

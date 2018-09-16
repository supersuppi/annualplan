package com.gxh.apserver.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gxh.apserver.dto.BudgetDTO;
import com.gxh.apserver.dto.PromoBudgetDTO;
import com.gxh.apserver.service.interfaces.BudgetService;

@RestController
@RequestMapping(value="/budget")
public class BudgetController extends BaseController{
	
	@Autowired
	private BudgetService budgetService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<List<PromoBudgetDTO>> getAllBudgetForSupplier(@PathVariable("id") Long supplierId) {
		List<PromoBudgetDTO> budgetDtoList;
		
		try {
			budgetDtoList = budgetService.getAllBudgets(supplierId);
		} catch (ParseException e) {
			logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return new ResponseEntity<List<PromoBudgetDTO>>(budgetDtoList, HttpStatus.OK);
	}

}

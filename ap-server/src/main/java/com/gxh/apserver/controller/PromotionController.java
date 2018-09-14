package com.gxh.apserver.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gxh.apserver.dto.AddOrRemoveProductRequestDTO;
import com.gxh.apserver.dto.AdminPromoDTO;
import com.gxh.apserver.dto.BudgetDTO;
import com.gxh.apserver.dto.PromoCommentDTO;
import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.dto.PromoSKUDTO;
import com.gxh.apserver.dto.StatusChangeDTO;
import com.gxh.apserver.exceptions.InvalidStatusException;
import com.gxh.apserver.exceptions.ResourceNotFoundException;
import com.gxh.apserver.service.interfaces.BudgetService;
import com.gxh.apserver.service.interfaces.PromotionService;

@RestController
@RequestMapping(value = "/promotion")
public class PromotionController extends BaseController {

    @Autowired
    private BudgetService budgetService;
    
    @Autowired
    PromotionService promotionService;
    
    @PostMapping(value = "/save")
    public ResponseEntity<String> savePromotion(@RequestBody PromoDTO promotion) {

        try {
            promotionService.saveSupplierPromo(promotion);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @PostMapping(value = "/product/save")
    public ResponseEntity<String> saveSelectedProducts(@RequestBody AddOrRemoveProductRequestDTO requestBody) {
    	
    	try {
    		promotionService.saveOrRemoveSelectedProducts(requestBody);
    	} catch (ParseException e) {
    		logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
	@GetMapping(value = "/product/fetch/{promoId}/{supplierId}")
	public ResponseEntity<PromoSKUDTO> getSelectedProducts(@PathVariable("promoId") Long promoId,@PathVariable("supplierId") Long supplierId,
			@RequestParam Long dmId, @RequestParam Long rowId, @RequestParam int promoCount) {

		PromoSKUDTO promoSKUDTO;

		try {
			promoSKUDTO = promotionService.getSavedProductsForPromoCount(promoId,supplierId,dmId, rowId, promoCount);
		} catch (ParseException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return new ResponseEntity<PromoSKUDTO>(promoSKUDTO, HttpStatus.OK);
	}
    
    @PostMapping(value = "/manager/status/update")
    public ResponseEntity<StatusChangeDTO> changePromotionStatus(@RequestBody StatusChangeDTO promotionStatus) {
        try {
            boolean success =  promotionService.changePromotionStatus(promotionStatus);
            StatusChangeDTO responseDTO = new StatusChangeDTO();
            responseDTO.setStatusChangeSuccess(success);

            return new ResponseEntity<StatusChangeDTO>(responseDTO,HttpStatus.OK);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/supplier/submit")
    public ResponseEntity<StatusChangeDTO> submitPromotion(@RequestBody StatusChangeDTO promotionStatus) {
        try {
            boolean success =  promotionService.changePromotionStatus(promotionStatus);
            StatusChangeDTO responseDTO = new StatusChangeDTO();
            responseDTO.setStatusChangeSuccess(success);

            return new ResponseEntity<StatusChangeDTO>(responseDTO,HttpStatus.OK);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/supplier/{id}/{pid}")
     ResponseEntity<PromoDTO> getSupplierPromo(@PathVariable("id") Long supplierID,
                                               @PathVariable("pid") Long promoID) {
        PromoDTO promoDTO;

        try {
            promoDTO = promotionService.getSupplierPromo(supplierID,promoID);
        } catch (ResourceNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (InvalidStatusException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ParseException exp) {
            logger.error(exp.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        ResponseEntity<PromoDTO> responseEntity = new ResponseEntity<PromoDTO>(promoDTO,
                HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/supplier/{id}")
    ResponseEntity<List<PromoDTO>> getAllSupplierPromo(@PathVariable("id") Long supplierID) {
        List<PromoDTO> promoDTO;

        try {
            promoDTO = promotionService.getAllSupplierPromo(supplierID);
        } catch (ResourceNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (InvalidStatusException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        ResponseEntity<List<PromoDTO>> responseEntity = new ResponseEntity<List<PromoDTO>>(promoDTO,
                HttpStatus.OK);

        return responseEntity;
    }
    
    @GetMapping("/manager/{sid}/{pid}")
    ResponseEntity<PromoDTO> getSupplierPromoForManager(@PathVariable("sid") Long supplierID,@PathVariable("pid") Long promoID) {
        PromoDTO promoDTO;

        try {
            promoDTO = promotionService.getSupplierPromoForManager(supplierID,promoID);
        } catch (ResourceNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (InvalidStatusException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ParseException exp) {
            logger.error(exp.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        ResponseEntity<PromoDTO> responseEntity = new ResponseEntity<PromoDTO>(promoDTO,
                HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping(value = "/manager/comment/save")
    public ResponseEntity<String> saveManagerComment(@RequestBody PromoCommentDTO promoCommentDTO) {

        try {
            promotionService.saveManagerComment(promoCommentDTO);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping("/active/{id}")
    ResponseEntity<List<AdminPromoDTO>> getAllActivePromotions(@PathVariable("id") Long supplierId) {
        List<AdminPromoDTO> activePromotionsList;

        try {
        	activePromotionsList = promotionService.getAllActivePromotionsForSupplier(supplierId);
        } catch (ResourceNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ParseException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        ResponseEntity<List<AdminPromoDTO>> responseEntity = new ResponseEntity<List<AdminPromoDTO>>(activePromotionsList,
                HttpStatus.OK);

        return responseEntity;
    }
    
    @PostMapping(value="/budget")
    public ResponseEntity<String> saveSupplierBudgetForPromoId(@RequestBody BudgetDTO budgetDto){
    	
    	try {
			budgetService.saveBudgetForPromotion(budgetDto);
		} catch (ParseException e) {
			logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}

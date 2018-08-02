package com.gxh.apserver.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.dto.StatusChangeDTO;
import com.gxh.apserver.exceptions.InvalidStatusException;
import com.gxh.apserver.exceptions.ResourceNotFoundException;
import com.gxh.apserver.service.interfaces.PromotionService;

@RestController
@RequestMapping(value = "/promotion")
public class PromotionController extends BaseController {

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
            boolean success =  promotionService.submitPromotion(promotionStatus);
            StatusChangeDTO responseDTO = new StatusChangeDTO();
            responseDTO.setStatusChangeSuccess(success);

            return new ResponseEntity<StatusChangeDTO>(responseDTO,HttpStatus.OK);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/supplier/{id}/{year}")
     ResponseEntity<PromoDTO> getSupplierPromo(@PathVariable("id") Long supplierID,
                                               @PathVariable("year") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date promoYear) {
        PromoDTO promoDTO;

        try {
            promoDTO = promotionService.getSupplierPromo(supplierID,promoYear);
        } catch (ResourceNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (InvalidStatusException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        ResponseEntity<PromoDTO> responseEntity = new ResponseEntity<PromoDTO>(promoDTO,
                HttpStatus.OK);

        return responseEntity;
    }
    
    @GetMapping("/manager/{id}/{year}")
    ResponseEntity<PromoDTO> getSupplierPromoForManager(@PathVariable("id") Long supplierID,
                                              @PathVariable("year") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date promoYear) {
        PromoDTO promoDTO;

        try {
            promoDTO = promotionService. getSupplierPromoForManager(supplierID,promoYear);
        } catch (ResourceNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (InvalidStatusException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        ResponseEntity<PromoDTO> responseEntity = new ResponseEntity<PromoDTO>(promoDTO,
                HttpStatus.OK);

        return responseEntity;
    }


}

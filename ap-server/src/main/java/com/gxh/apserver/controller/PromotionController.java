package com.gxh.apserver.controller;

import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.exceptions.InvalidStatusException;
import com.gxh.apserver.exceptions.ResourceNotFoundException;
import com.gxh.apserver.service.interfaces.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

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

    @GetMapping("/supplier/{id}/{year}")
     ResponseEntity<PromoDTO> getSupplierPromo(@PathVariable("id") Long supplierID,
                                               @PathVariable("year") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date promoYear) {
        PromoDTO promoDTO;

        try {
            promoDTO = promotionService. getSupplierPromo(supplierID,promoYear);
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

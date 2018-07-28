package com.gxh.apserver.controller;

import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.service.interfaces.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/supplier/{id}")
     ResponseEntity<PromoDTO> getSupplierPromo(@PathVariable("id") Long supplierID) {
        PromoDTO promoDTO = promotionService. getSupplierActivePromo(supplierID);

        ResponseEntity<PromoDTO> responseEntity = new ResponseEntity<PromoDTO>(promoDTO,
                HttpStatus.OK);

        return responseEntity;
    }

}

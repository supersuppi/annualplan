package com.gxh.apserver.controller;

import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.service.interfaces.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/promotion")
public class PromotionController extends BaseController {

    @Autowired
    PromotionService promotionService;

    @PostMapping(value = "/save")
    public ResponseEntity<String> savePromotion() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/supplier/{id}")
     ResponseEntity<List<PromoDTO>> getSupplierPromo(@PathVariable("id") Long supplierID) {
        List<PromoDTO> promoDTO = promotionService.getSupplierActivePromo(supplierID);

        ResponseEntity<List<PromoDTO>> responseEntity = new ResponseEntity<List<PromoDTO>>(promoDTO,
                HttpStatus.OK);

        return responseEntity;
    }

}

package com.gxh.apserver.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.AdminPromoDTO;
import com.gxh.apserver.service.interfaces.AdminService;

/*
 * A Controller class to handle all admin feature request
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/promotion/save")
    public ResponseEntity<String> saveSelectedProducts(@RequestBody AdminPromoDTO promo) {
        try {
            adminService.savePromotion(promo);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/activate/promotion/{pid}")
    public ResponseEntity<String> activatePromotionStatus(@PathVariable("pid") Long promoID) {
        boolean success = adminService.activatePromotion(promoID);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/promotion/{status}")
    public ResponseEntity<List<AdminPromoDTO>> getPromotion(@PathVariable("status") String status) {
        try {
            List<AdminPromoDTO> adminPromoDTO = adminService.getPromotionsByStatus(PromotionStatus.valueOf(status));
            return new ResponseEntity<List<AdminPromoDTO>>(adminPromoDTO, HttpStatus.OK);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/promotion/all")
    public ResponseEntity<List<AdminPromoDTO>> getAllPromotion() {
        try {
            List<AdminPromoDTO> adminPromoDTO = adminService.getAllPromotions();
            return new ResponseEntity<List<AdminPromoDTO>>(adminPromoDTO, HttpStatus.OK);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/promotion/find/{pid}")
    public ResponseEntity<AdminPromoDTO> getPromotion(@PathVariable("pid") Long pid) {
        try {
            AdminPromoDTO adminPromoDTO = adminService.getPromotionByID(pid);
            return new ResponseEntity<AdminPromoDTO>(adminPromoDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/promotion/update")
    public ResponseEntity<String> updatePromotion(@RequestBody AdminPromoDTO promo) {
        try {
            adminService.updatePromotion(promo);
            return new ResponseEntity<String>(new String(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

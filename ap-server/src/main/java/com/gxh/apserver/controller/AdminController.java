package com.gxh.apserver.controller;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.AddOrRemoveProductRequestDTO;
import com.gxh.apserver.dto.AdminPromoDTO;
import com.gxh.apserver.service.interfaces.AdminService;
import com.gxh.apserver.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/promotion/save")
    public ResponseEntity<String> saveSelectedProducts(@RequestBody AdminPromoDTO requestBody) {
        try {
            boolean status = adminService.savePromotion(requestBody);
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/promotion/activate")
    public ResponseEntity<String> activatePromotionStatus() {
        boolean success = adminService.activatePromotion();
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/promotion/draft")
    public ResponseEntity<String> getDraftPromotion() {
        try {
            AdminPromoDTO adminPromoDTO = adminService.getPromotionByStatus(PromotionStatus.DRAFT);
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

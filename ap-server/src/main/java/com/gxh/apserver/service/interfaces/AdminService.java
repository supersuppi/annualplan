package com.gxh.apserver.service.interfaces;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.AdminPromoDTO;

import java.text.ParseException;
import java.util.List;

public interface AdminService {
    boolean savePromotion(AdminPromoDTO adminPromoDTO) throws ParseException;
    List<AdminPromoDTO> getPromotionsByStatus(PromotionStatus status) throws ParseException;
    List<AdminPromoDTO> getAllPromotions() throws ParseException;
    boolean activatePromotion(Long promoID);
    AdminPromoDTO getPromotionByID(Long promoID);
    boolean updatePromotion(AdminPromoDTO adminPromoDTO) throws ParseException;
}

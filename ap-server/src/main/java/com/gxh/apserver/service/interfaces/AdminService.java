package com.gxh.apserver.service.interfaces;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.AdminPromoDTO;

import java.text.ParseException;

public interface AdminService {
    boolean savePromotion(AdminPromoDTO adminPromoDTO) throws ParseException;
    AdminPromoDTO getPromotionByStatus(PromotionStatus status) throws ParseException;
    boolean activatePromotion();
}

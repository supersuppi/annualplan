package com.gxh.apserver.service.interfaces;

import java.text.ParseException;
import java.util.Date;

import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.dto.StatusChangeDTO;
import com.gxh.apserver.exceptions.InvalidStatusException;
import com.gxh.apserver.exceptions.ResourceNotFoundException;

public interface PromotionService {
    PromoDTO getSupplierPromo(Long supplierID,Date promoYear) throws ResourceNotFoundException,
            InvalidStatusException;
    Boolean saveSupplierPromo(PromoDTO promoDTO) throws ParseException;
    Boolean changePromotionStatus(StatusChangeDTO promoDTO) throws ParseException;
    PromoDTO getSupplierPromoForManager(Long supplierID,Date promoYear) throws ResourceNotFoundException,
            InvalidStatusException;
    Boolean submitPromotion(StatusChangeDTO statusDTO) throws ParseException;
}

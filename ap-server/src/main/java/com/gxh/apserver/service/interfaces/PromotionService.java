package com.gxh.apserver.service.interfaces;

import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.exceptions.InvalidStatusException;
import com.gxh.apserver.exceptions.ResourceNotFoundException;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface PromotionService {
    PromoDTO getSupplierPromo(Long supplierID,Date promoYear) throws ResourceNotFoundException,
            InvalidStatusException;
    Boolean saveSupplierPromo(PromoDTO promoDTO) throws ParseException;
    PromoDTO getSupplierPromoForManager(Long supplierID,Date promoYear) throws ResourceNotFoundException,
            InvalidStatusException;
}

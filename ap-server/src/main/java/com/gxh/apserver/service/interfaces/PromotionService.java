package com.gxh.apserver.service.interfaces;

import com.gxh.apserver.dto.PromoDTO;

import java.text.ParseException;
import java.util.List;

public interface PromotionService {
    PromoDTO getSupplierActivePromo(Long supplierID);
    Boolean saveSupplierPromo(PromoDTO promoDTO) throws ParseException;
}

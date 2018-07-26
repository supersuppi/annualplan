package com.gxh.apserver.service.interfaces;

import com.gxh.apserver.dto.PromoDTO;

import java.util.List;

public interface PromotionService {
    PromoDTO getSupplierActivePromo(Long supplierID);
}

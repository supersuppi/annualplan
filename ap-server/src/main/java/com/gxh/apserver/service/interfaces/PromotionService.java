package com.gxh.apserver.service.interfaces;

import com.gxh.apserver.dto.PromoDTO;

import java.util.List;

public interface PromotionService {
    List<PromoDTO> getSupplierActivePromo(Long supplierID);
}

package com.gxh.apserver.service.interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.gxh.apserver.dto.AddOrRemoveProductRequestDTO;
import com.gxh.apserver.dto.ProductDTO;
import com.gxh.apserver.dto.PromoCommentDTO;
import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.dto.PromoSKUDTO;
import com.gxh.apserver.dto.StatusChangeDTO;
import com.gxh.apserver.exceptions.InvalidStatusException;
import com.gxh.apserver.exceptions.ResourceNotFoundException;

public interface PromotionService {
    PromoDTO getSupplierPromo(Long supplierID,String promoYear) throws ResourceNotFoundException,
            InvalidStatusException,ParseException;
    
    List<PromoDTO> getAllSupplierPromo(Long supplierID) throws ResourceNotFoundException,
            InvalidStatusException;
    
    boolean saveSupplierPromo(PromoDTO promoDTO) throws ParseException;
    
    boolean saveManagerComment(PromoCommentDTO promoCommentDTO) throws ParseException;
    
    boolean changePromotionStatus(StatusChangeDTO promoDTO) throws ParseException;
    
    PromoDTO getSupplierPromoForManager(Long supplierID,String promoYear) throws ResourceNotFoundException,
            InvalidStatusException,ParseException;
    
    public void saveOrRemoveSelectedProducts( AddOrRemoveProductRequestDTO requestBody ) throws ParseException;

	public PromoSKUDTO getSavedProductsForPromoCount(Long promoId, Long dmId, Long rowId, int promoCount)
		throws ParseException;
}

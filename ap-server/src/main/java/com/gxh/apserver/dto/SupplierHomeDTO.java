package com.gxh.apserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gxh.apserver.entity.Manager;
import com.gxh.apserver.entity.Notification;
import lombok.Data;

import java.util.List;

@Data
public class SupplierHomeDTO extends HomeDTO {
    private List<PromoYearDetailDTO> promoYearDetails;
    private String activePromoYear;
    private String supplierName;
    private Long supplierCode;
    private Long supplierID;
    private List<ManagerDTO> managers;
    private List<SupManCommentDTO> comments;
}

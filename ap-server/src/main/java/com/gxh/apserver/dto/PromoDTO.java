package com.gxh.apserver.dto;

import java.util.List;

import com.gxh.apserver.constants.PromotionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PromoDTO {
   private String promoyear;
   private Long userid;
   private Boolean isEditable;
   private Integer version;
   private PromotionStatus status;
   private List<RateCardDTO> ratecards;
   private List<ProductDTO> products;
}

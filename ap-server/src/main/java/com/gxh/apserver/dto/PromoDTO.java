package com.gxh.apserver.dto;

import java.util.List;
import java.util.Map;

import com.gxh.apserver.constants.AnnualPromotionStatus;
import com.gxh.apserver.constants.PromotionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PromoDTO extends BaseDTO {
   private String promoyear;
   private Long userid;
   private Long promo_id;
   private Long annualpromo_id;
   private Boolean isEditable;
   private Integer version;
   private AnnualPromotionStatus status;
   private BudgetDTO budget;
   private List<RateCardDTO> ratecards;
   private List<BrandProductDTO> products;
}

package com.gxh.apserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PromoDTO {
   private String promoyear;
   private Long supplierID;
   private List<RateCardDTO> ratecards;
   private List<ProductDTO> products;
}

package com.gxh.apserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.gxh.apserver.constants.PromotionType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromoSKUDTO {
    private Integer promo_count;
    private List<ProductDTO> products_selected;
    private PromotionType promoType;
    private String promoName;
}

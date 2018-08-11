package com.gxh.apserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PromoYearDetailDTO {
    private String promo_year;
    private BudgetDTO budget;
}

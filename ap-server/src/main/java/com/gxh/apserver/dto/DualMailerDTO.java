package com.gxh.apserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DualMailerDTO {
    private String id;
    private Integer value;
    private List<PromoSKUDTO> promosku;
}

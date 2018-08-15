package com.gxh.apserver.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddOrRemoveProductRequestDTO {

    private Integer promoCount;
    private Long dmId;
    private Long rcId;
    private Long promoId;
    private List<ProductDTO> productsSelected;
    private List<ProductDTO> productsDeselected;
    
}

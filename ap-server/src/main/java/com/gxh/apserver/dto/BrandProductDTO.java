package com.gxh.apserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BrandProductDTO {
    private String name;
    private List<ProductDTO> promosku;
}

package com.gxh.apserver.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RateCardDTO {
    private String pname;
    private String pcode;
    private Float prate;
    private Integer max_tile;
    private Integer min_tile;
    private Integer max_product;
    private Integer nash_rc;
    private List<DualMailerDTO> dualmailers;
}

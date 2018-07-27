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
    private List<DualMailerDTO> dualmailers;
}

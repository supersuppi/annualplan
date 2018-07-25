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
   private String pname;
   private String pcode;
   private Float prate;
   private Boolean isEditable;
   private List<DualMailerDTO> dualmailers;
}

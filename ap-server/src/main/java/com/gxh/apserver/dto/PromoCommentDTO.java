package com.gxh.apserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PromoCommentDTO {
    private String promoYear;
    private Long promoID;
    private Long annualpromoID;
    private Long supplierid;
    private Long managerid;
    private String comment;
}

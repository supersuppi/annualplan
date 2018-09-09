package com.gxh.apserver.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class AdminPromoDTO {
    private String name;
    private String userName;
    private Long pid;
    private String pstatus;
    private List<AdminMailerDTO> dualmailers;
    private List<AdminRCDTO> ratecards;
    private List<SupplierDTO> suppliers;
}

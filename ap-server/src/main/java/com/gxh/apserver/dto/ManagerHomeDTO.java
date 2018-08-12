package com.gxh.apserver.dto;

import lombok.Data;

import java.util.List;

@Data
public class ManagerHomeDTO extends HomeDTO {
    private Long managerID;
    private String mamangerName;
    private List<SupplierHomeDTO> suppliers;
}

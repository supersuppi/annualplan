package com.gxh.apserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupplierDTO {
    private String supplierName;
    private Long supplierCode;
    private Long supplierID;
}

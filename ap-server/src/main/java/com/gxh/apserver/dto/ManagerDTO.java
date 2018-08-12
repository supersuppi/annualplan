package com.gxh.apserver.dto;

import com.gxh.apserver.constants.ManagerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ManagerDTO {
    private Long managerID;
    private String managerName;
    private ManagerType managerType;
}

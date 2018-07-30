package com.gxh.apserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDTO {
    protected boolean hasError;
    protected String errorMessage;
}

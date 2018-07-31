package com.gxh.apserver.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class StatusChangeDTO {
	private String currentStatus;
	private String statusChangeTo;
	private String promoYear;
	private Boolean statusChangeSuccess;
	private Long supplierid;
}

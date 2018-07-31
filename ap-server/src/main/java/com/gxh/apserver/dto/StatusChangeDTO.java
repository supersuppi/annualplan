package com.gxh.apserver.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusChangeDTO {
	private String currentStatus;
	private String statusChangeTo;
	private Date promoYear;
	private Boolean statusChangeSuccess;
	private Long supplierid;
}

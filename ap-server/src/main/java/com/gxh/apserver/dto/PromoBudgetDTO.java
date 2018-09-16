package com.gxh.apserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PromoBudgetDTO {

	private String promotionName;
	private Long budgetAllocated;
	private Long budgetRemaining;
	private Long budgetUtilized;
}

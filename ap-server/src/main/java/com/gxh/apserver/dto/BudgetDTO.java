package com.gxh.apserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BudgetDTO {
    private Long allocated;
    private Long used;
    private Long diff;
}

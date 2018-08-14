package com.gxh.apserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SupManCommentDTO {
    private String senderName;
    private String comment;
    private Date sentOn;
}

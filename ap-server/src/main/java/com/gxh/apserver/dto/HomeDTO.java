package com.gxh.apserver.dto;

import com.gxh.apserver.entity.Notification;
import lombok.Data;

import java.util.List;

@Data
public class HomeDTO {
    private Long userID;
    private String userName;
    private String role;
    List<AdminPromoDTO> activePromotions;
    private List<Notification> notifications;
}

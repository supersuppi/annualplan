package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "promotion_level_sku")
public class PromotionLevelSKU {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "promo_id")
    private Long promo;

    @Column(name = "ratecard_id")
    private Long rateCard;

    @Column(name = "dualmailer_id")
    private Long dualMailer;

    @Column(name = "tiles_selected",length=1)
    private Integer value;
}

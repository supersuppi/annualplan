package com.gxh.apserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "promotion_level_ratecard")
public class PromotionLevelRateCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

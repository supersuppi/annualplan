package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.gxh.apserver.constants.PromotionType;

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

    @Column(name = "product_id")
    private Long product;
    
    @Column(name = "promo_count")
    private int promoCount;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "promotion_type")
    private PromotionType promoType;
    
    @Column(name="Promotion_name")
    private String promoName;
}

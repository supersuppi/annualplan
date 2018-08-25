package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pricing")
public class Pricing {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "price_list_cost",length=6)
    private Float priceListCost;

    @Column(name = "price_list_terms",length=6)
    private Float priceListTermPercent;

    @Column(name = "gdc_cost",length=6)
    private Float GDCCost;

    @Column(name = "gdc_markup",length=6)
    private Float GDCMarkUpPercent;

    @Column(name = "gdc_sell_cost",length=6)
    private Float GDCSellCost;

    @Column(name = "direct_cost",length=6)
    private Float directCost;

    @Column(name = "tier1_price",length=6)
    private Float tier1Price;

    @Column(name = "tier1_margin",length=6)
    private Float tier1Margin;

    @Column(name = "tier1_margin_percent",length=6)
    private Float tier1MarginPercent;

    @Column(name = "tier2_price",length=6)
    private Float tier2Price;

    @Column(name = "tier2_margin",length=6)
    private Float tier2Margin;

    @Column(name = "tier2_margin_percent",length=6)
    private Float tier2MarginPercent;

    @Column(name = "tier3_price",length=6)
    private Float tier3Price;

    @Column(name = "tier3_margin",length=6)
    private Float tier3Margin;

    @Column(name = "tier3_margin_percent",length=6)
    private Float tier3MarginPercent;
}

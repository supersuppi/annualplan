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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "price_list_cost")
    private int priceListCost;

    @Column(name = "price_list_terms_%")
    private float priceListTermPercent;

    @Column(name = "gdc_cost")
    private int GDCCost;

    @Column(name = "gdc_markUp_%")
    private float GDCMarkUpPercent;

    @Column(name = "gdc_sell_cost")
    private int GDCSellCost;

    @Column(name = "direct_cost")
    private int directCost;

    @Column(name = "tier1_price")
    private int tier1Price;

    @Column(name = "tier1_margin_$")
    private int tier1Margin;

    @Column(name = "tier1_margin_%")
    private float tier1MarginPercent;

    @Column(name = "tier2_price")
    private int tier2Price;

    @Column(name = "tier2_margin_$")
    private int tier2Margin;

    @Column(name = "tier2_margin_%")
    private float tier2MarginPercent;

    @Column(name = "tier3_price")
    private int tier3Price;

    @Column(name = "tier3_margin_$")
    private int tier3Margin;

    @Column(name = "tier3_margin_%")
    private float tier3MarginPercent;
}

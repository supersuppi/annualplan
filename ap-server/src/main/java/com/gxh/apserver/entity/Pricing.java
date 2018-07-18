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

    @Column(name = "price_list_cost",length=6)
    private Float priceListCost;

    @Column(name = "price_list_terms_%",length=6)
    private Float priceListTermPercent;

    @Column(name = "gdc_cost",length=6)
    private Float GDCCost;

    @Column(name = "gdc_markUp_%",length=6)
    private Float GDCMarkUpPercent;

    @Column(name = "gdc_sell_cost",length=6)
    private Float GDCSellCost;

    @Column(name = "direct_cost",length=6)
    private Float directCost;

    @Column(name = "tier1_price",length=6)
    private Float tier1Price;

    @Column(name = "tier1_margin_$",length=6)
    private Float tier1Margin;

    @Column(name = "tier1_margin_%",length=6)
    private Float tier1MarginPercent;

    @Column(name = "tier2_price",length=6)
    private Float tier2Price;

    @Column(name = "tier2_margin_$",length=6)
    private Float tier2Margin;

    @Column(name = "tier2_margin_%",length=6)
    private Float tier2MarginPercent;

    @Column(name = "tier3_price",length=6)
    private Float tier3Price;

    @Column(name = "tier3_margin_$",length=6)
    private Float tier3Margin;

    @Column(name = "tier3_margin_%",length=6)
    private Float tier3MarginPercent;
}

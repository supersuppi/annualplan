package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "promo")
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "vendor_code")
    private String vendorCode;

    @Column(name = "rate_card_code")
    private String rateCardCode;

    @Column(name = "dual_mailer_number")
    private String dualMailerNumber;

    @Column(name = "value")
    private int value;

    @Column(name = "dollar_value")
    private int dollarValue;

    @Column(name = "supplier_yearly_budget")
    private String supplierearlyBudget;

    @Column(name = "space_allocation")
    private int spaceAllocation;

    @Column(name = "ratecard_id")
    private int ratecardID;

    @Column(name = "mailer_id")
    private int mailerID;

    @Column(name = "yearly_ratecard_spend")
    private int yearlyRateCardSpend;

    @Column(name = "montly_dual_mailer_budget")
    private int montlyDualMailerBudget;

    @Column(name = "total_spend_by_supplier")
    private Long totalSpendBySupplier;

    @Column(name = "spend_difference")
    private Long spendDifference;

    @Column(name = "gxh_id")
    private Long GXHID;

    @Column(name = "banner")
    private String banner;

    @Column(name = "discount_amount")
    private Long discountAmount;

    @Column(name = "promo_cost")
    private Long promoCost;

    @Column(name = "member_promo_cost")
    private Long memberPromoCost;

    @Column(name = "rebate")
    private String rebate;

    @Column(name = "comments")
    private String comments;

    @Column(name = "promoRRP")
    private String promoRRP;

    @Column(name = "living_reward")
    private String livingReward;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promogroup_id", referencedColumnName = "id")
    private PromoGroup promoGroup;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promomechic_id", referencedColumnName = "id")
    private PromoMechic promoMechic;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offercallout_id", referencedColumnName = "id")
    private OfferCallOut offerCallout;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_manager_id", referencedColumnName = "id")
    private User categoryManager;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;
}

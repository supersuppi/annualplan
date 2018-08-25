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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "vendor_code")
    private Long vendorCode;

    @Column(name = "rate_card_code",length = 5)
    private String rateCardCode;

    @Column(name = "dual_mailer_number",length = 5)
    private String dualMailerNumber;

    @Column(name = "value")
    private Integer value;

    @Column(name = "dollar_value",length = 4)
    private Float dollarValue;

    @Column(name = "supplier_yearly_budget")
    private String supplierearlyBudget;

    @Column(name = "space_allocation")
    private Integer spaceAllocation;

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

    @Column(name = "banner",length = 50)
    private String banner;

    @Column(name = "discount_amount")
    private Float discountAmount;

    @Column(name = "promo_cost", length = 4)
    private Float promoCost;

    @Column(name = "member_promo_cost", length = 4)
    private Float memberPromoCost;

    @Column(name = "rebate", length = 50)
    private String rebate;

    @Column(name = "comments", length = 100)
    private String comments;

    @Column(name = "promoRRP", length = 50)
    private String promoRRP;

    @Column(name = "living_reward", length = 50)
    private String livingReward;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promogroup_id", referencedColumnName = "id")
    private PromoGroup promoGroup;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promomechic_id", referencedColumnName = "id")
    private PromoMechanic promoMechic;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offercallout_id", referencedColumnName = "id")
    private OfferCallOut offerCallout;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_manager_id", referencedColumnName = "id")
    private User categoryManager;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ratecard_id", referencedColumnName = "id")
    private RateCard rateCard;

}

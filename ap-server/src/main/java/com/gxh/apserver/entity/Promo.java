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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User categoryManager;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

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

    @Column(name = "Mailer ID")
    private int mailerID;

}

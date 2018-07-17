package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "gxh_id")
    private Long GXHID;

    @Column(name = "plu_code")
    private Long PLUCode;

    @Column(name = "product_code")
    private Long productCode;

    @Column(name = "vendor_ax_code")
    private Long vendorAXCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User categoryManager;

    @Column(name = "description")
    private String description;

    @Column(name = "warehouse_or_direct")
    private String warehouseOrDirect;

    @Column(name = "range_status")
    private String rangeStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    private Pricing price;

    @Column(name = "marketing_brand_name")
    private String marketingBrandName;

    @Column(name = "marketing_short_name")
    private String marketingShortName;

    @Column(name = "marketing_size")
    private int marketingSize;

    @Column(name = "marketing_description")
    private String marketingDescription;

    @Column(name = "marketing_mandatory_description")
    private String marketingMandatoryDescription;

    @Column(name = "marketing supplier legal name")
    private String marketingSupplierLegalName;

    @Column(name = "pharmacist_only")
    private String pharmacistOnly;

    @Column(name = "only_in_pharmacy")
    private String onlyInPharmacy;

    @Column(name = "product_GUID")
    private Long ProductGUID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "barcode_id", referencedColumnName = "id")
    private Barcode barcode;

}

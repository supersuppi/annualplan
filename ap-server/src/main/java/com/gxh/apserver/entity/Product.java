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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @Column(name = "description",length=100)
    private String description;

    @Column(name = "warehouse_or_direct",length=1)
    private String warehouseOrDirect;

    @Column(name = "range_status",length=6)
    private String rangeStatus;

    @Column(name = "marketing_brand_name",length=60)
    private String marketingBrandName;

    @Column(name = "marketing_short_name",length=60)
    private String marketingShortName;

    @Column(name = "marketing_size",length=20)
    private String marketingSize;

    @Column(name = "marketing_description",length=100)
    private String marketingDescription;

    @Column(name = "marketing_mandatory_description",length=100)
    private String marketingMandatoryDescription;

    @Column(name = "marketing_supplier_legal_name",length=80)
    private String marketingSupplierLegalName;

    @Column(name = "pharmacist_only",length=3)
    private String pharmacistOnly;

    @Column(name = "only_in_pharmacy",length=3)
    private String onlyInPharmacy;

    @Column(name = "product_GUID",length=100)
    private String ProductGUID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "barcode_id", referencedColumnName = "id")
    private Barcode barcode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    private Pricing price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_manager_id", referencedColumnName = "id")
    private User categoryManager;
}

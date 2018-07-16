package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "supplier")
public class Supplier {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "vendor_ax_code")
    private String vendorAXCode;

    @Column(name = "supplier_captain")
    private String supplierCaptain;

    @Column(name = "supplier")
    private String supplier;

}

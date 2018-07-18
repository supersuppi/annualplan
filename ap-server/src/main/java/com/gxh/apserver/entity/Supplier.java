package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "vendor_ax_code")
    private Long vendorAXCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_captain_id", referencedColumnName = "id")
    private User supplierCaptain;

    @Column(name = "supplier",length=100)
    private String supplier;
}

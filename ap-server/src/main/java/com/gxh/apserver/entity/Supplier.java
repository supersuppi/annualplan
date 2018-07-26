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

    @Column(name = "vendor_name",length = 100)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_captain_id", referencedColumnName = "id")
    private User supplierCaptain;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_user_id", referencedColumnName = "id")
    private User supplier;
}

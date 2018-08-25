package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "vendor_ax_code")
    private Long vendorAXCode;

    @Column(name = "vendor_name",length = 100)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_user_id", referencedColumnName = "id")
    private User supplierAppUser;

    @ManyToMany(fetch=FetchType.LAZY, mappedBy = "suppliers")
    private Set<Manager> managers = new HashSet<Manager>();
}

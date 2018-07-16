package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "barcode")
public class Barcode {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "barcode1")
    private String barcode1;

    @Column(name = "barcode2")
    private String barcode2;

    @Column(name = "barcode3")
    private String barcode3;

}

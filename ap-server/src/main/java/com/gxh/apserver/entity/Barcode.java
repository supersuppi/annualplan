package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "barcode")
public class Barcode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "barcode1")
    private Long barcode1;

    @Column(name = "barcode2")
    private Long barcode2;

    @Column(name = "barcode3")
    private Long barcode3;
}

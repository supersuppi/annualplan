package com.gxh.apserver.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",length=100)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_group_id", referencedColumnName = "id")
    private BrandGroup brandGroup;
}

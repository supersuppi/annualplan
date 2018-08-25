package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "category",length=50)
    private String category;

    @Column(name = "sub_category",length=50)
    private String subCategory;

    @Column(name = "department",length=50)
    private String department;
}

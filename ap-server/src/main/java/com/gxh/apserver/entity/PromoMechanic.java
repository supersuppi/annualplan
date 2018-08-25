package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "promo_mechanic")
public class PromoMechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "min_tile_allocation",length=2)
    private Integer minTileAllocation = 0;

    @Column(name = "max_tile_allocation",length=2)
    private Integer maxTileAllocation = 0;

    @Column(name = "max_product_allocation",length=2)
    private Integer maxProductAllocation = 0;

    @Column(name = "Xnash",length=2)
    private Integer timesOfNash = 0;

}

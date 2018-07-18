package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ratecard")
public class RateCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",length=100)
    private String name;

    @Column(name = "code",length=4)
    private String code;

    @Column(name = "max_tile_allocation",length=2)
    private Integer maxTileAllocation;

    @Column(name = "rc_dollar",length=6)
    private Float rateCardDollar;
}

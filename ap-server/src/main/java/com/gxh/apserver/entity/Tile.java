package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tile")
public class Tile {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "year_id")
    private int yearID;

    @Column(name = "catalogue_number")
    private Long catalogueNumber;

    @Column(name = "nos_pages")
    private int numberOfPages;

    @Column(name = "available_tiles")
    private int availableTiles;

    @Column(name = "professional_service_tiles")
    private int professionalServiceTile;

    @Column(name = "loyalty_tiles")
    private int loyaltyTiles;
}

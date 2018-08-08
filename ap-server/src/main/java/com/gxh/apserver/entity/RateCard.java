package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Column(name = "rc_dollar",length=6)
    private Float rateCardDollar;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "promotion_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Promotion promotion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promoMechanic_id", referencedColumnName = "id")
    private PromoMechanic promoMechanic;

}

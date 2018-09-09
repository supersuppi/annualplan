package com.gxh.apserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "supplier_promotion_budget")
public class SupplierPromotionBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id", referencedColumnName = "id")
    private Promotion promotion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @Column(name = "promotion_budget_allocated")
    private Long budgetAllocated;

    @Column(name = "promotion_budget")
    private Long totalBudget;
}

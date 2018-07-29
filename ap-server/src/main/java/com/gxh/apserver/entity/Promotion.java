package com.gxh.apserver.entity;

import javax.persistence.*;

import com.gxh.apserver.constants.PromotionStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "promotion")
public class Promotion {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @Column(name = "year")
    @Temporal(TemporalType.DATE)
    private Date year;

    @Enumerated(EnumType.STRING)
	@Column(name = "status",length=10)
    private PromotionStatus status;
}

package com.gxh.apserver.entity;

import javax.persistence.*;

import com.gxh.apserver.constants.AnnualPromotionStatus;
import com.gxh.apserver.constants.PromotionStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "annual_promotion")
public class AnnualPromotion {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promotion_id", referencedColumnName = "id")
    private Promotion promo;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @Enumerated(EnumType.STRING)
	@Column(name = "status",length=20)
    private AnnualPromotionStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    @CreatedDate
    private Date modifiedAt = new Date();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdByUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "modified_by", referencedColumnName = "id")
    private User modifiedByUser;
}

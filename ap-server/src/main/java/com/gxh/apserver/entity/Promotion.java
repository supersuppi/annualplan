package com.gxh.apserver.entity;

import javax.persistence.*;

import com.gxh.apserver.constants.PromotionStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "promotion")
public class Promotion {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;
}

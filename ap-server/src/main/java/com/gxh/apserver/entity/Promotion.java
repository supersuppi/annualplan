package com.gxh.apserver.entity;

import com.gxh.apserver.constants.AnnualPromotionStatus;
import com.gxh.apserver.constants.PromotionStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length=150)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",length=20)
    private PromotionStatus status;

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

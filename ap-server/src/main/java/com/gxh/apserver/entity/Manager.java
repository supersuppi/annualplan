package com.gxh.apserver.entity;

import com.gxh.apserver.constants.ManagerType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type",length = 50)
    private ManagerType type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_user_id", referencedColumnName = "id")
    private User managerAppUser;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "manager_supplier",
            joinColumns = { @JoinColumn(name = "MANAGER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "SUPPLIER_ID") })
    private Set<Supplier> suppliers = new HashSet<>();
}

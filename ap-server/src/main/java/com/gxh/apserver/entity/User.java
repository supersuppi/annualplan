package com.gxh.apserver.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "users")
// @JsonIgnoreProperties(value = {"password", "updatedAt"}, allowGetters = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private int active;

    @OneToOne
    private Role role;
}


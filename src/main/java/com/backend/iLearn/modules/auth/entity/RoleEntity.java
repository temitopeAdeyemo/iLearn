package com.backend.iLearn.modules.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name = "role")
@Data
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}

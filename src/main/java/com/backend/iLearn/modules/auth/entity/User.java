package com.backend.iLearn.modules.auth.entity;

import com.backend.iLearn.modules.admin.entity.Admin;
import com.backend.iLearn.modules.student.entity.Student;
import com.backend.iLearn.modules.tutor.entity.Tutor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

@Entity(name = "LearnUsers")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class User implements UserDetails {

    @Id
    @Column
    @Nullable
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email", unique = true)
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "password")
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one digit, one uppercase letter, one lowercase letter, and one special character (@#$%^&+=)")
    private String password;

    @ToString.Exclude// To avoid Handler dispatch failed: java.lang.StackOverflowError from loading bidirectional data infinitely
    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL /*CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH*/ /*, CascadeType.DETACH*/}/*, fetch = FetchType.LAZY*/)
    private Admin adminProfile;

    @ToString.Exclude// To avoid Handler dispatch failed: java.lang.StackOverflowError from loading bidirectional data infinitely
    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL /*CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH*/ /*, CascadeType.DETACH*/}/*, fetch = FetchType.LAZY*/)
    private Tutor tutorProfile;

    @JsonIgnore// tells Jackson not to include these fields in the serialized JSON response
    @ToString.Exclude// To avoid Handler dispatch failed: java.lang.StackOverflowError from loading bidirectional data infinitely
    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL /*CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH*/ /*, CascadeType.DETACH*/}/*, fetch = FetchType.LAZY*/)
    private Student studentProfile;

    @JsonIgnore// tells Jackson not to include these fields in the serialized JSON response
    @ToString.Exclude// To avoid Handler dispatch failed: java.lang.StackOverflowError from loading bidirectional data infinitely
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<RoleEntity> roles;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date updatedAt;

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public boolean passwordMatch(String password, String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, password);
    }


    @PrePersist
    protected void onCreate() {
        System.out.println("Creating user data.");
    }

    @PreUpdate
    protected void onUpdate() {
        System.out.println("Updating admin data.");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert roles to a collection of GrantedAuthority objects
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());
    }

    //    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(roles.name()));
//    }

    @JsonIgnore// tells Jackson not to include these fields in the serialized JSON response
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore// tells Jackson not to include these fields in the serialized JSON response
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    @JsonIgnore// tells Jackson not to include these fields in the serialized JSON response
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    @JsonIgnore// tells Jackson not to include these fields in the serialized JSON response
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @JsonIgnore // tells Jackson not to include these fields in the serialized JSON response
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }
}

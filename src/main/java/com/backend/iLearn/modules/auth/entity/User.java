package com.backend.iLearn.modules.auth.entity;

import com.backend.iLearn.modules.admin.entity.Admin;
import com.backend.iLearn.modules.student.entity.Student;
import com.backend.iLearn.modules.tutor.entity.Tutor;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "first_name")
    @NotNull(message = "First name cannot be null")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name cannot be null")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    private String lastName;

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

    @JsonIgnore// tells Jackson not to include these fields in the serialized JSON response and avoid joined data from loading bidirectional data infinitely.
//    @ToString.Exclude// To avoid Handler dispatch failed: java.lang.StackOverflowError from loading bidirectional data infinitely
    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL /*CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH*/ /*, CascadeType.DETACH*/}/*, fetch = FetchType.LAZY*/)
    private Admin adminProfile;

    @JsonIgnore// tells Jackson not to include these fields in the serialized JSON response and avoid joined data from loading bidirectional data infinitely.
//    @ToString.Exclude// To avoid Handler dispatch failed: java.lang.StackOverflowError from loading bidirectional data infinitely
    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL /*CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH*/ /*, CascadeType.DETACH*/}/*, fetch = FetchType.LAZY*/)
    private Tutor tutorProfile;

    @JsonIgnore// tells Jackson not to include these fields in the serialized JSON response and avoid joined data from loading bidirectional data infinitely.
//    @ToString.Exclude// To avoid Handler dispatch failed: java.lang.StackOverflowError from loading bidirectional data infinitely
    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL /*CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH*/ /*, CascadeType.DETACH*/}/*, fetch = FetchType.LAZY*/)
    private Student studentProfile;

    @JsonIgnore// tells Jackson not to include these fields in the serialized JSON response and avoid joined data from loading bidirectional data infinitely.
//    @ToString.Exclude// To avoid Handler dispatch failed: java.lang.StackOverflowError from loading bidirectional data infinitely
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

    @JsonIgnore// tells Jackson not to include these fields in the serialized JSON response when the user is fetched
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore// tells Jackson not to include these fields in the serialized JSON response when the user is fetched
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    @JsonIgnore// tells Jackson not to include these fields in the serialized JSON response when the user is fetched
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    @JsonIgnore// tells Jackson not to include these fields in the serialized JSON response when the user is fetched
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @JsonIgnore // tells Jackson not to include these fields in the serialized JSON response when the user is fetched
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }
}

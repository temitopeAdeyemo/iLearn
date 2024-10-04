package com.backend.iLearn.modules.admin.entity;

import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.chat.entity.Chat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    @Id
    @Column
    @Nullable
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "adminSenderId", orphanRemoval = true, cascade = {CascadeType.ALL /*CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH*/ /*, CascadeType.DETACH*/}, fetch = FetchType.LAZY)
    @Column(name = "sent_chats")
    private Set<Chat> sentChats = new HashSet<>();

    @OneToMany(mappedBy = "adminReceiverId", orphanRemoval = true, cascade = {CascadeType.ALL /*CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH*/ /*, CascadeType.DETACH*/}, fetch = FetchType.LAZY)
    @Column(name = "receiver_chats")
    private Set<Chat> receivedChats = new HashSet<>();

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        System.out.println("Creating admin data.");
    }

    @PreUpdate
    protected void onUpdate() {
        System.out.println("Updating admin data.");
    }
}

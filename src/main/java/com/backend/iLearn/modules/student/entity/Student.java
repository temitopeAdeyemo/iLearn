package com.backend.iLearn.modules.student.entity;

import com.backend.iLearn.modules.auth.Enum.Role;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.chat.entity.Chat;
import com.backend.iLearn.modules.course.entity.Course;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @Column
    @Nullable
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();

    @Column(name = "first_name")
    @NotNull(message = "First name cannot be null")
    @Size(min = 1, max = 25, message = "First name must be between 1 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name cannot be null")
    @Size(min = 1, max = 25, message = "Last name must be between 1 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    private String lastName;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Student_courses",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "studentSenderId", orphanRemoval = true, cascade = {CascadeType.ALL /*CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH*/ /*, CascadeType.DETACH*/}, fetch = FetchType.LAZY)
    @Column(name = "sent_chats")
    private Set<Chat> sent_Chats = new HashSet<>();

    @OneToMany(mappedBy = "studentReceiverId", orphanRemoval = true, cascade = {CascadeType.ALL /*CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH*/ /*, CascadeType.DETACH*/}, fetch = FetchType.LAZY)
    @Column(name = "received_chats")
    private Set<Chat> receivedChats = new HashSet<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        System.out.println("Creating student data.");
    }

    @PreUpdate
    protected void onUpdate() {
        System.out.println("Updating student data.");
    }
}

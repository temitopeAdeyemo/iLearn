package com.backend.iLearn.modules.course.entity;

import com.backend.iLearn.modules.student.entity.Student;
import com.backend.iLearn.modules.tutor.entity.Tutor;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Course {
    @Id
    @Column
    @Nullable
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();

    @Column(name = "title", nullable = false)
    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 250 characters")
    private String title;

    @Column(name = "description")
    @Size(max = 500)
    private String description;

    @OneToMany(mappedBy = "course", orphanRemoval = true, cascade = { CascadeType.ALL /*CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH*/ /*, CascadeType.DETACH*/}, fetch = FetchType.LAZY)
    private Set<CourseContent> courseContents = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor")
    private Tutor tutor;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        System.out.println("Creating course data.");
    }

    @PreUpdate
    protected void onUpdate() {
        System.out.println("Updating course data.");
    }
}

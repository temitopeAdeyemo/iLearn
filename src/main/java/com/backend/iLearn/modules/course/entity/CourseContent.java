//package com.backend.iLearn.modules.course.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.annotation.Nullable;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//import java.util.Date;
//import java.util.UUID;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//public class CourseVideos {
//    @Id
//    @Column
//    @Nullable
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id = UUID.randomUUID();
//
//    @Column(name = "url", nullable = false)
//    @NotNull(message = "Video URL cannot be null")
//    @Size(min = 5, message = "URL must be at least 5 characters long")
//    private String url;
//
//    @Column(name = "title", nullable = false)
//    @NotNull(message = "Video title cannot be null")
//    @Size(min = 2, message = "Video title must be at least 2 characters long")
//    private String title;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "course")
//    private Course course;
//
//    @Column(name = "created_at", nullable = false, updatable = false)
//    @CreationTimestamp
//    private Date createdAt;
//
//    @Column(name = "updated_at", nullable = false, updatable = false)
//    @CreationTimestamp
//    private Date updatedAt;
//
//    @PrePersist
//    protected void onCreate() {
//        System.out.println("Creating video data.");
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        System.out.println("Updating video data.");
//    }
//}


package com.backend.iLearn.modules.course.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
import java.util.UUID;

@Entity(name = "course_content")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)// Could be joined or table per class.
@DiscriminatorColumn(name = "content_type")
public abstract class CourseContent {
    @Id
    @Column
    @Nullable
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();

    @NotNull(message = "Sequence number cannot be null")
    @Column(name = "sequence_number")
    private int sequenceNumber;

    @Column(name = "title", nullable = false)
    @NotNull(message = "Video title cannot be null")
    @Size(min = 2, message = "Video title must be at least 2 characters long")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    private Course course;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        System.out.println("Creating video data.");
    }

    @PreUpdate
    protected void onUpdate() {
        System.out.println("Updating video data.");
    }
}

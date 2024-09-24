package com.backend.iLearn.modules.chat.entity;

import com.backend.iLearn.modules.admin.entity.Admin;
import com.backend.iLearn.modules.student.entity.Student;
import com.backend.iLearn.modules.tutor.entity.Tutor;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @Id
    @Column
    @Nullable
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutorSender")
    private Tutor tutorSenderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentSender")
    private Student studentSenderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminSender")
    private Admin adminSenderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutorReceiver")
    private Tutor tutorReceiverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentReceiver")
    private Student studentReceiverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminReceiver")
    private Admin adminReceiverId;

    @Column(name = "message_content", nullable = false, length = 500)
    @NotNull(message = "Message content cannot be null")
    @Size(min = 1, max = 500, message = "Message content must be between 1 and 500 characters")
    private String messageContent;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        System.out.println("Creating chat data.");
    }

    @PreUpdate
    protected void onUpdate() {
        System.out.println("Updating chat data.");
    }
}

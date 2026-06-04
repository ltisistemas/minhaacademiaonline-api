package com.minhaacademiaonline.api.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "student_belt_status")
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class StudentBeltStatus {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private Student student;

    @ManyToOne
    private Belts belt;

    private Integer degrees; // 0, 1, 2, 3, 4

    private LocalDateTime lastPromotionDate; // Quando ele pegou esta faixa ou este grau

    // Total de aulas computadas desde a última promoção
    private Long totalAttendancesSinceLastPromotion;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

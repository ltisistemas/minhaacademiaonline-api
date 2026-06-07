package com.minhaacademiaonline.api.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "belt_requirements")
@Getter
@Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class BeltRequirement {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Tenant tenant; // A regra pertence a esta academia específica

    @ManyToOne
    private Belt belt; // Qual faixa estamos regulando?

    private Integer minMonths; // Tempo mínimo (ex: 12 meses)
    private Integer minAttendances; // Frequência mínima necessária (ex: 100 aulas)

    // Campo para "notas" ou "observações" do professor
    @Column(columnDefinition = "TEXT")
    private String criteriaNotes;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

package com.minhaacademiaonline.api.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "plans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    @OneToMany(mappedBy = "plan")
    private Set<Tenant> tenants = new HashSet<>();

    private String name;
    @Column(columnDefinition = "TEXT")
    private String content;

    private BigDecimal fee;

    @Column(name = "max_students")
    private Long maxStudents;

    @Enumerated(EnumType.STRING)
    private PlanStatus status = PlanStatus.ACTIVE;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum PlanStatus {
        ACTIVE,
        INACTIVE
    }


}

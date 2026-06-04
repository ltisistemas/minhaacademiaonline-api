package com.minhaacademiaonline.api.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Subscription {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant; // Qual academia paga?

    private BigDecimal amount; // Valor pago (pode ser diferente do plano se houver desconto)

    private LocalDate nextDueDate; // Data de vencimento da próxima fatura

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status; // ACTIVE, OVERDUE, CANCELED

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum SubscriptionStatus {
        PENDING, PAID, ACTIVE, OVERDUE, CANCELED
    }
}
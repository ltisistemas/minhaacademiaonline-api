package com.minhaacademiaonline.api.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tenants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = true, name = "legal_name")
    private String legalName;

    @Column(nullable = false, name = "trade_name")
    private String tradeName;

    @Column(nullable = true)
    private String nif;
    @Column(nullable = true, unique = true)
    private String subdomain;

    private String gatewayApiToken;
    private String whatsappApiToken;

    @Enumerated(EnumType.STRING)
    private TenantStatus status = TenantStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private TenantPaidStatus paidStatus = TenantPaidStatus.PENDING;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    private Set<Attendance> attendances = new HashSet<>();

    @Column(name = "monthly_fee")
    private BigDecimal monthlyFee;

    public enum TenantStatus {
        ACTIVE,
        INACTIVE,
        SUSPEND
    }

    public enum TenantPaidStatus {
        PENDING,
        PAID
    }

    public String generteSubdomain() {
        String slug = getTradeName()
                        .toLowerCase()
                        .trim()
                        .replaceAll("[^a-z0-9]", "-")
                        .replaceAll("-+", "-")
                        .replaceAll("^-|-$", "");

        slug = slug + "-" + UUID.randomUUID().toString().substring(0,4);
        return slug;
    }

    public TenantPaidStatus getPaidStatus() {
        return plan.getFee().equals(BigDecimal.ZERO)
                ? TenantPaidStatus.PAID
                : TenantPaidStatus.PENDING;
    }
}

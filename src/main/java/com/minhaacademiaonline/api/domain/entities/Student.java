package com.minhaacademiaonline.api.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_owner_id")
    private User accountOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "belt_id")
    private Belt belt;

    @Column(nullable = false)
    private String name;
    private String nickname;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = true)
    private String password;
    @Column(nullable = false)
    private String phonenumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<StudentTenant> studentTenants = new HashSet<>();

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

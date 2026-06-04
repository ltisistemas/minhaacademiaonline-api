package com.minhaacademiaonline.api.infra.repositories;

import com.minhaacademiaonline.api.domain.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, UUID> {
    Optional<Tenant> findById(UUID id);

    Optional<Tenant> findTenantBySubdomain(String subdomain);

    Optional<Object> findTenantById(UUID id);
}

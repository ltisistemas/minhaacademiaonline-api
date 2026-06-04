package com.minhaacademiaonline.api.infra.repositories;

import com.minhaacademiaonline.api.domain.entities.Tenant;
import com.minhaacademiaonline.api.domain.entities.User;
import com.minhaacademiaonline.api.domain.entities.UserTenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserTenantRepository extends JpaRepository<UserTenant, UUID> {
    Optional<UserTenant> findUserTenantByUser(User user);

    Optional<UserTenant> findUserTenantByTenant(Tenant tenant);
}

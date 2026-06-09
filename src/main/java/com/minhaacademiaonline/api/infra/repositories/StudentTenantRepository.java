package com.minhaacademiaonline.api.infra.repositories;

import com.minhaacademiaonline.api.domain.entities.Student;
import com.minhaacademiaonline.api.domain.entities.StudentTenant;
import com.minhaacademiaonline.api.domain.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentTenantRepository extends JpaRepository<StudentTenant, UUID> {
    Optional<StudentTenant> findStudentTenantByStudent(Student student);

    List<StudentTenant> findAllByTenant(Tenant tenant);  
}

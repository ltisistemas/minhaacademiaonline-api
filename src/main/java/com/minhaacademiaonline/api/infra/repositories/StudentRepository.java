package com.minhaacademiaonline.api.infra.repositories;

import com.minhaacademiaonline.api.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findStudentById(UUID id);
    List<Student> findAllByTenantsId(UUID tenantId);
}

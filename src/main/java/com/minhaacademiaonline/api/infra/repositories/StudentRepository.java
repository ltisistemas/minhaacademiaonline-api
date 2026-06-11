package com.minhaacademiaonline.api.infra.repositories;

import com.minhaacademiaonline.api.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findStudentById(UUID id);

    Optional<Student> findStudentByUsername(String username);
    List<Student> findAllByStudentTenantsTenantId(UUID tenantId);

    @Query("SELECT DISTINCT s FROM Student s " +
            "LEFT JOIN FETCH s.belt " +
            "LEFT JOIN FETCH s.graduationHistories " +
            "WHERE s.id = :id")
    Optional<Student> findByIdWithBeltAndHistory(@Param("id") UUID id);

    @Query("SELECT DISTINCT s FROM Student s " +
            "LEFT JOIN FETCH s.attendances " +
            "WHERE s.id = :id")
    Optional<Student> findByIdWithAttendances(@Param("id") UUID id);
}

package com.yusuf.fitnessmanagement.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

    long countByFitnessClass_FitnessClassId(Long fitnessClassId);

    // Eagerly fetch member and fitnessClass to avoid LazyInitializationException
    @Query("SELECT e FROM Enrollment e JOIN FETCH e.member JOIN FETCH e.fitnessClass")
    List<Enrollment> findAllWithMemberAndClass();

    // Fetch a specific enrollment by ID with member and fitnessClass eagerly loaded
    @Query("SELECT e FROM Enrollment e JOIN FETCH e.member JOIN FETCH e.fitnessClass WHERE e.id = :id")
    Optional<Enrollment> findByIdWithMemberAndClass(@Param("id") EnrollmentId id);
}
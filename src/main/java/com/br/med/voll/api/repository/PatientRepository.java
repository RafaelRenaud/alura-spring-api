package com.br.med.voll.api.repository;

import com.br.med.voll.api.model.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByIsActiveTrue(Pageable pageable);

    @Query(
            "SELECT TRUE FROM Patient patient WHERE patient.id = :patientId AND patient.isActive = TRUE"
    )
    Boolean exists(Long patientId);
}

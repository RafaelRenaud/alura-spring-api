package com.br.med.voll.api.repository;

import com.br.med.voll.api.model.entity.Consult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {
    Boolean existsByDoctorIdAndConsultDate(Long doctorId, LocalDateTime consultDate);

    Boolean existsByPatientIdAndConsultDateBetween(Long patientId, LocalDateTime fisrtTime, LocalDateTime lastTime);
}

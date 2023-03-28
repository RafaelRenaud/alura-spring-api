package com.br.med.voll.api.repository;

import com.br.med.voll.api.model.entity.Doctor;
import com.br.med.voll.api.model.type.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

    Page<Doctor> findAllByIsActiveTrue(Pageable pageable);

    @Query(
            "SELECT doctor FROM Doctor doctor WHERE " +
                    "doctor.isActive = true AND doctor.speciality = :speciality " +
                    "AND doctor.id NOT IN(" +
                    "SELECT consult.doctor.id FROM Consult consult WHERE " +
                    "consult.consultDate = :date) " +
                    "ORDER BY RAND() " +
                    "LIMIT 1"
    )
    Doctor selectRandomDoctor(Speciality speciality, LocalDateTime date);

    @Query(
            "SELECT doctor.isActive FROM Doctor doctor WHERE " +
                    "doctor.id = :doctorId"
    )
    Boolean findIsActiveById(Long doctorId);
}

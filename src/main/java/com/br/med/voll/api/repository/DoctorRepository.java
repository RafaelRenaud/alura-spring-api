package com.br.med.voll.api.repository;

import com.br.med.voll.api.model.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

    Page<Doctor> findAllByIsActiveTrue(Pageable pageable);
}
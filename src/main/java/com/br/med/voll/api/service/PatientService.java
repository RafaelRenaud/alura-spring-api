package com.br.med.voll.api.service;

import com.br.med.voll.api.model.dto.patient.get.ListPatientResponseDTO;
import com.br.med.voll.api.model.dto.patient.post.CreatePatientRequestDTO;
import com.br.med.voll.api.model.dto.patient.post.CreatePatientResponseDTO;
import com.br.med.voll.api.model.dto.patient.put.UpdatePatientRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {
    CreatePatientResponseDTO createPatient(CreatePatientRequestDTO createPatientRequestDTO);
    Page<ListPatientResponseDTO> listPatient(Pageable pagination);
    void updatePatient(UpdatePatientRequestDTO updatePatientRequestDTO);
    void inactivePatient(Long id);
    ListPatientResponseDTO getPatient(Long id);
}

package com.br.med.voll.api.service.impl;

import com.br.med.voll.api.model.dto.patient.get.ListPatientResponseDTO;
import com.br.med.voll.api.model.dto.patient.post.CreatePatientRequestDTO;
import com.br.med.voll.api.model.dto.patient.post.CreatePatientResponseDTO;
import com.br.med.voll.api.model.dto.patient.put.UpdatePatientRequestDTO;
import com.br.med.voll.api.model.entity.Patient;
import com.br.med.voll.api.repository.PatientRepository;
import com.br.med.voll.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    @Transactional
    public CreatePatientResponseDTO createPatient(CreatePatientRequestDTO createPatientRequestDTO) {
        Patient patient = new Patient(createPatientRequestDTO);
        CreatePatientResponseDTO responseDTO = new CreatePatientResponseDTO(patientRepository.save(patient).getId().toString());
        return responseDTO;
    }

    @Override
    public Page<ListPatientResponseDTO> listPatient(Pageable pagination) {
        Page<ListPatientResponseDTO> patientList = patientRepository.findAllByIsActiveTrue(pagination).map(
                ListPatientResponseDTO::new
        );

        return patientList;
    }

    @Override
    @Transactional
    public void updatePatient(UpdatePatientRequestDTO updatePatientRequestDTO) {
        Patient patient = patientRepository.getReferenceById(updatePatientRequestDTO.id());
        patient.updatePatient(updatePatientRequestDTO);
    }

    @Override
    @Transactional
    public void inactivePatient(Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        patient.inactive();
    }

    @Override
    public ListPatientResponseDTO getPatient(Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        return new ListPatientResponseDTO(patient);
    }
}

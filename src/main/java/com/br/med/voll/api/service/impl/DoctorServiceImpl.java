package com.br.med.voll.api.service.impl;

import com.br.med.voll.api.model.dto.doctors.get.ListDoctorResponseDTO;
import com.br.med.voll.api.model.dto.doctors.post.CreateDoctorRequestDTO;
import com.br.med.voll.api.model.dto.doctors.post.CreateDoctorResponseDTO;
import com.br.med.voll.api.model.dto.doctors.put.UpdateDoctorRequestDTO;
import com.br.med.voll.api.model.entity.Doctor;
import com.br.med.voll.api.repository.DoctorRepository;
import com.br.med.voll.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    @Transactional
    public CreateDoctorResponseDTO createDoctor(CreateDoctorRequestDTO createDoctorRequestDTO) {
        Doctor doctor = new Doctor(createDoctorRequestDTO);
        CreateDoctorResponseDTO responseDTO = new CreateDoctorResponseDTO(doctorRepository.save(doctor).getId().toString());
        return responseDTO;
    }

    @Override
    public Page<ListDoctorResponseDTO> listDoctor(Pageable pagination) {
        Page<ListDoctorResponseDTO> doctorList = doctorRepository.findAllByIsActiveTrue(pagination).map(
                ListDoctorResponseDTO::new
        );

        return doctorList;
    }

    @Override
    @Transactional
    public void updateDoctor(UpdateDoctorRequestDTO updateDoctorRequestDTO) {
        Doctor doctor = doctorRepository.getReferenceById(updateDoctorRequestDTO.id());
        doctor.updateDoctorInformations(updateDoctorRequestDTO);
    }

    @Override
    @Transactional
    public void inactiveDoctor(Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.inactiveDoctor();
    }
}

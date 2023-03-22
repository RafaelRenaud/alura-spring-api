package com.br.med.voll.api.service;

import com.br.med.voll.api.model.dto.doctor.get.ListDoctorResponseDTO;
import com.br.med.voll.api.model.dto.doctor.post.CreateDoctorRequestDTO;
import com.br.med.voll.api.model.dto.doctor.post.CreateDoctorResponseDTO;
import com.br.med.voll.api.model.dto.doctor.put.UpdateDoctorRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorService {

    CreateDoctorResponseDTO createDoctor(CreateDoctorRequestDTO createDoctorRequestDTO);
    Page<ListDoctorResponseDTO> listDoctor(Pageable pagination);
    void updateDoctor(UpdateDoctorRequestDTO updateDoctorRequestDTO);
    void inactiveDoctor(Long id);
    ListDoctorResponseDTO getDoctor(Long id);
}

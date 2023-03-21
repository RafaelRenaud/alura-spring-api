package com.br.med.voll.api.controller;

import com.br.med.voll.api.model.dto.doctors.get.ListDoctorResponseDTO;
import com.br.med.voll.api.model.dto.doctors.post.CreateDoctorRequestDTO;
import com.br.med.voll.api.model.dto.doctors.post.CreateDoctorResponseDTO;
import com.br.med.voll.api.model.dto.doctors.put.UpdateDoctorRequestDTO;
import com.br.med.voll.api.service.impl.DoctorServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorServiceImpl doctorService;

    @PostMapping
    public ResponseEntity<CreateDoctorResponseDTO> createDoctor(
            @RequestBody
            @Valid
            CreateDoctorRequestDTO requestDTO
            ){
        CreateDoctorResponseDTO responseDTO = doctorService.createDoctor(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ListDoctorResponseDTO>> listDoctors(
            @PageableDefault(size = 5, page = 0, sort = {
                    "crm"
            }, direction = Sort.Direction.DESC)
            Pageable pagination){
        Page<ListDoctorResponseDTO> doctors = doctorService.listDoctor(pagination);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateDoctor(
            @RequestBody
            @Valid
            UpdateDoctorRequestDTO requestDTO
    ){
        doctorService.updateDoctor(requestDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{doctor_id}")
    public ResponseEntity inactiveDoctor(
            @PathVariable(name = "doctor_id") Long doctorId
    ){
        doctorService.inactiveDoctor(doctorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

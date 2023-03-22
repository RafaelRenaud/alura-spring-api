package com.br.med.voll.api.controller;

import com.br.med.voll.api.model.dto.doctor.get.ListDoctorResponseDTO;
import com.br.med.voll.api.model.dto.doctor.post.CreateDoctorRequestDTO;
import com.br.med.voll.api.model.dto.doctor.post.CreateDoctorResponseDTO;
import com.br.med.voll.api.model.dto.doctor.put.UpdateDoctorRequestDTO;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorServiceImpl doctorService;

    @PostMapping
    public ResponseEntity createDoctor(
            @RequestBody
            @Valid
            CreateDoctorRequestDTO requestDTO,
            UriComponentsBuilder uriComponentsBuilder
            ){

        CreateDoctorResponseDTO responseDTO = doctorService.createDoctor(requestDTO);
        URI uri = uriComponentsBuilder.path("/doctors/{doctor_id}").buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
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

    @GetMapping("/{doctor_id}")
    public ResponseEntity getDoctor(
            @PathVariable(name = "doctor_id") Long doctorId
    ){
        ListDoctorResponseDTO responseDTO = doctorService.getDoctor(doctorId);
        return ResponseEntity.ok(responseDTO);
    }
}

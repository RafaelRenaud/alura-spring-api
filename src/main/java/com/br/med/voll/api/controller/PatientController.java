package com.br.med.voll.api.controller;

import com.br.med.voll.api.model.dto.patient.get.ListPatientResponseDTO;
import com.br.med.voll.api.model.dto.patient.post.CreatePatientRequestDTO;
import com.br.med.voll.api.model.dto.patient.post.CreatePatientResponseDTO;
import com.br.med.voll.api.model.dto.patient.put.UpdatePatientRequestDTO;
import com.br.med.voll.api.service.impl.PatientServiceImpl;
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
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientServiceImpl patientService;

    @PostMapping
    public ResponseEntity createPatient(
            @RequestBody
            @Valid
                    CreatePatientRequestDTO requestDTO,
            UriComponentsBuilder uriComponentsBuilder
    ){

        CreatePatientResponseDTO responseDTO = patientService.createPatient(requestDTO);
        URI uri = uriComponentsBuilder.path("/patients/{patient_id}").buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ListPatientResponseDTO>> listPatient(
            @PageableDefault(size = 5, page = 0, direction = Sort.Direction.DESC)
                    Pageable pagination){
        Page<ListPatientResponseDTO> patients = patientService.listPatient(pagination);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updatePatient(
            @RequestBody
            @Valid
                    UpdatePatientRequestDTO requestDTO
    ){
        patientService.updatePatient(requestDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{patient_id}")
    public ResponseEntity inactivePatient(
            @PathVariable(name = "patient_id") Long patientId
    ){
        patientService.inactivePatient(patientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{patient_id}")
    public ResponseEntity getPatient(
            @PathVariable(name = "patient_id") Long patientId
    ){
        ListPatientResponseDTO responseDTO = patientService.getPatient(patientId);
        return ResponseEntity.ok(responseDTO);
    }
}

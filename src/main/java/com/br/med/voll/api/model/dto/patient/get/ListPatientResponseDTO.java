package com.br.med.voll.api.model.dto.patient.get;

import com.br.med.voll.api.model.entity.Patient;

public record ListPatientResponseDTO (
        String id,
        String name,
        String email
){
    public ListPatientResponseDTO(Patient patient){
        this(patient.getId().toString(), patient.getName(), patient.getEmail());
    }
}

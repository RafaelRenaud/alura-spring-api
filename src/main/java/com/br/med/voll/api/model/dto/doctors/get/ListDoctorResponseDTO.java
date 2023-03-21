package com.br.med.voll.api.model.dto.doctors.get;

import com.br.med.voll.api.model.entity.Doctor;
import com.br.med.voll.api.model.type.Speciality;

public record ListDoctorResponseDTO(
    String id,
    String name,
    String email,
    String crm,
    Speciality speciality
) {

    public ListDoctorResponseDTO(Doctor doctor){
        this(doctor.getId().toString(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpeciality());
    }
}

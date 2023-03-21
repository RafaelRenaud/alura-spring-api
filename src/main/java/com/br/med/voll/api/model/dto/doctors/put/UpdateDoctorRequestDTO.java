package com.br.med.voll.api.model.dto.doctors.put;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdateDoctorRequestDTO(

        @NotNull
        Long id,
        String name,
        String phone,

        @Valid
        UpdateDoctorAddressRequestDTO address
) {
}

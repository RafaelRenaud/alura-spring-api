package com.br.med.voll.api.model.dto.doctor.put;

import com.br.med.voll.api.model.dto.address.UpdateAddressRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdateDoctorRequestDTO(

        @NotNull
        Long id,
        String name,
        String phone,

        @Valid
        UpdateAddressRequestDTO address
) {
}

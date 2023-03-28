package com.br.med.voll.api.model.dto.patient.put;

import com.br.med.voll.api.model.dto.address.UpdateAddressRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdatePatientRequestDTO(

        @NotNull
        Long id,
        String name,
        String phone,

        @Valid
        UpdateAddressRequestDTO address
) {
}

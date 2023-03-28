package com.br.med.voll.api.model.dto.patient.post;

import com.br.med.voll.api.model.dto.address.CreateAddressRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record CreatePatientRequestDTO(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        @CPF
        String cpf,

        @NotNull
        @Valid
        CreateAddressRequestDTO address
) {
}

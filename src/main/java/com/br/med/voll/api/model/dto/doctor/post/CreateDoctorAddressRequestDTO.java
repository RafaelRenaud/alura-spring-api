package com.br.med.voll.api.model.dto.doctor.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateDoctorAddressRequestDTO (

        @NotBlank
        String street,

        @NotBlank
        String neighborhood,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String zipcode,

        @NotBlank
        String city,

        @NotBlank
        String uf,

        String number,

        String complement
) {
}

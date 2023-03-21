package com.br.med.voll.api.model.dto.doctors.post;

import com.br.med.voll.api.model.type.Speciality;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateDoctorRequestDTO (

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Speciality speciality,

        @NotBlank
        String phone,

        @NotNull
        @Valid
        CreateDoctorAddressRequestDTO address
){

}

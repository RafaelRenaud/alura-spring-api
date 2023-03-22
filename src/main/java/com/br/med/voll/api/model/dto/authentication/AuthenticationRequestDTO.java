package com.br.med.voll.api.model.dto.authentication;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDTO(

        @NotBlank
        String login,

        @NotBlank
        String password
) {
}

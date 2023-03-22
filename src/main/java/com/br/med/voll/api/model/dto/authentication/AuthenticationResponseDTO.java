package com.br.med.voll.api.model.dto.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationResponseDTO(
        String timestamp,

        @JsonProperty("trace_id")
        String traceId,

        @JsonProperty("access_token")
        String accessToken
) {
}

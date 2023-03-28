package com.br.med.voll.api.model.dto.consult;

import com.br.med.voll.api.model.type.Speciality;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultScheduleRequestDTO(
        Long doctorId,

        Speciality speciality,

        @NotNull
        Long patientId,

        @NotNull
        @Future
                @JsonAlias(value = "date")
        LocalDateTime consultDate
) {
}

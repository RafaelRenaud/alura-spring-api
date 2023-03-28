package com.br.med.voll.api.model.dto.consult;

import com.br.med.voll.api.model.entity.Consult;

import java.time.LocalDateTime;

public record ConsultScheduleResponseDTO(
        Long consultId,
        Long doctorId,
        Long patientId,
        LocalDateTime consultDate
) {

    public ConsultScheduleResponseDTO(Consult consult){
        this(consult.getId(),consult.getDoctor().getId(),consult.getPatient().getId(),consult.getConsultDate());
    }
}

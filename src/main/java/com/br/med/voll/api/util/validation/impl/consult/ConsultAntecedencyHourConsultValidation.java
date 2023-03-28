package com.br.med.voll.api.util.validation.impl.consult;

import com.br.med.voll.api.exception.ValidationException;
import com.br.med.voll.api.model.dto.consult.ConsultScheduleRequestDTO;
import com.br.med.voll.api.util.validation.ConsultValidation;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ConsultAntecedencyHourConsultValidation implements ConsultValidation {
    @Override
    public void validate(ConsultScheduleRequestDTO consultScheduleRequestDTO) {
        LocalDateTime date = consultScheduleRequestDTO.consultDate();
        LocalDateTime now = LocalDateTime.now();
        Long minutesDifference = Duration.between(now, date).toMinutes();

        if(minutesDifference < 30){
            throw new ValidationException("Consulta deve ser agendada com antecedência de 30 minutos no mínimo!");
        }

    }
}

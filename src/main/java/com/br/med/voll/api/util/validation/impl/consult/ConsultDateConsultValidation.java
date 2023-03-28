package com.br.med.voll.api.util.validation.impl.consult;

import com.br.med.voll.api.exception.ValidationException;
import com.br.med.voll.api.model.dto.consult.ConsultScheduleRequestDTO;
import com.br.med.voll.api.util.validation.ConsultValidation;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ConsultDateConsultValidation implements ConsultValidation {
    @Override
    public void validate(ConsultScheduleRequestDTO consultScheduleRequestDTO) {
        LocalDateTime date = consultScheduleRequestDTO.consultDate();
        Boolean isSunday = date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        Boolean beforeWorkHour = date.getHour() < 7;
        Boolean afterWorkHour = date.getHour() > 18;

        if(isSunday || beforeWorkHour || afterWorkHour){
            throw new ValidationException("Consulta fora do Horário de funcionamento da clínica!");
        }
    }
}

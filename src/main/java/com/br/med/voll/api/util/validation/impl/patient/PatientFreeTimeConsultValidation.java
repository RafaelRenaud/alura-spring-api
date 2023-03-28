package com.br.med.voll.api.util.validation.impl.patient;

import com.br.med.voll.api.exception.ValidationException;
import com.br.med.voll.api.model.dto.consult.ConsultScheduleRequestDTO;
import com.br.med.voll.api.repository.ConsultRepository;
import com.br.med.voll.api.util.validation.ConsultValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PatientFreeTimeConsultValidation implements ConsultValidation {

    @Autowired
    private ConsultRepository consultRepository;

    @Override
    public void validate(ConsultScheduleRequestDTO data) {
        LocalDateTime fisrtTime = data.consultDate().withHour(7);
        LocalDateTime lastTime = data.consultDate().withHour(18);
        Boolean patientHaveAnotherConsult = consultRepository.existsByPatientIdAndConsultDateBetween(data.patientId(), fisrtTime, lastTime);

        if(patientHaveAnotherConsult){
            throw new ValidationException("Paciente já possui consulta agendada no dia!");
        }
    }
}

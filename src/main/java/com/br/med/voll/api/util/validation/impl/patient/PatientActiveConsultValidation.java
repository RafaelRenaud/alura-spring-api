package com.br.med.voll.api.util.validation.impl.patient;

import com.br.med.voll.api.exception.ValidationException;
import com.br.med.voll.api.model.dto.consult.ConsultScheduleRequestDTO;
import com.br.med.voll.api.repository.PatientRepository;
import com.br.med.voll.api.util.validation.ConsultValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientActiveConsultValidation implements ConsultValidation {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void validate(ConsultScheduleRequestDTO consultScheduleRequestDTO) {
        Boolean isActive = patientRepository.exists(consultScheduleRequestDTO.patientId());

        if(!isActive){
            throw new ValidationException("Paciente inativo no sistema!");
        }
    }
}

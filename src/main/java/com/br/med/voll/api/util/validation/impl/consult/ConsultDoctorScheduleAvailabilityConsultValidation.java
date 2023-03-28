package com.br.med.voll.api.util.validation.impl.consult;

import com.br.med.voll.api.exception.ValidationException;
import com.br.med.voll.api.model.dto.consult.ConsultScheduleRequestDTO;
import com.br.med.voll.api.repository.ConsultRepository;
import com.br.med.voll.api.util.validation.ConsultValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultDoctorScheduleAvailabilityConsultValidation implements ConsultValidation {

    @Autowired
    private ConsultRepository consultRepository;

    @Override
    public void validate(ConsultScheduleRequestDTO consultScheduleRequestDTO) {
        Boolean existsAnotherSchedule = consultRepository.existsByDoctorIdAndConsultDate(
                consultScheduleRequestDTO.doctorId(), consultScheduleRequestDTO.consultDate());

        if(existsAnotherSchedule){
            throw new ValidationException("Médico com horário já agendado!");
        }

    }
}

package com.br.med.voll.api.util.validation.impl.doctor;

import com.br.med.voll.api.exception.ValidationException;
import com.br.med.voll.api.model.dto.consult.ConsultScheduleRequestDTO;
import com.br.med.voll.api.repository.DoctorRepository;
import com.br.med.voll.api.util.validation.ConsultValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorActiveConsultValidation implements ConsultValidation {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void validate(ConsultScheduleRequestDTO consultScheduleRequestDTO) {
        if(consultScheduleRequestDTO.doctorId() != null){
            return;
        }

        Boolean doctorIsActive = doctorRepository.findIsActiveById(consultScheduleRequestDTO.doctorId());

        if(!doctorIsActive){
            throw new ValidationException("Consulta não pode ser agendada com o médico selecionado!");
        }
    }
}

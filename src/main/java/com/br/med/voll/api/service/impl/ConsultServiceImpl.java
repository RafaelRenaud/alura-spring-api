package com.br.med.voll.api.service.impl;

import com.br.med.voll.api.exception.InexistentEntityException;
import com.br.med.voll.api.exception.ValidationException;
import com.br.med.voll.api.model.dto.consult.ConsultScheduleRequestDTO;
import com.br.med.voll.api.model.dto.consult.ConsultScheduleResponseDTO;
import com.br.med.voll.api.model.entity.Consult;
import com.br.med.voll.api.model.entity.Doctor;
import com.br.med.voll.api.model.entity.Patient;
import com.br.med.voll.api.repository.ConsultRepository;
import com.br.med.voll.api.repository.DoctorRepository;
import com.br.med.voll.api.repository.PatientRepository;
import com.br.med.voll.api.service.ConsultService;
import com.br.med.voll.api.util.validation.ConsultValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultServiceImpl implements ConsultService {

    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ConsultValidation> consultValidators;

    @Override
    @Transactional
    public ConsultScheduleResponseDTO schedule(ConsultScheduleRequestDTO consultScheduleRequestDTO) {

        if(!patientRepository.existsById(consultScheduleRequestDTO.patientId())){
            throw new InexistentEntityException("Paciente Inexistente na Base de Dados!");
        }

        if(consultScheduleRequestDTO.doctorId() != null && !doctorRepository.existsById(consultScheduleRequestDTO.doctorId())){
            throw new InexistentEntityException("Médico Inexistente na Base de Dados!");
        }

        consultValidators.forEach( validator -> validator.validate(consultScheduleRequestDTO));

        Patient patient = patientRepository.getReferenceById(consultScheduleRequestDTO.patientId());
        Doctor doctor = selectDoctor(consultScheduleRequestDTO);

        if(doctor == null){
            throw new ValidationException("Não existe médico disponível nesta data!");
        }

        Consult consult = new Consult(null, doctor, patient, consultScheduleRequestDTO.consultDate());
        return new ConsultScheduleResponseDTO(consultRepository.save(consult));

    }

    private Doctor selectDoctor(ConsultScheduleRequestDTO consultScheduleRequestDTO) {
        if(consultScheduleRequestDTO.doctorId() != null){
            return doctorRepository.getReferenceById(consultScheduleRequestDTO.doctorId());
        }else if(consultScheduleRequestDTO.speciality() == null){
            throw new InexistentEntityException("Especialidade obrigatória quando o médico não for escolhido!");
        }

        return doctorRepository.selectRandomDoctor(consultScheduleRequestDTO.speciality(), consultScheduleRequestDTO.consultDate());
    }
}

package com.br.med.voll.api.util.validation;

import com.br.med.voll.api.model.dto.consult.ConsultScheduleRequestDTO;

public interface ConsultValidation {
    void validate(ConsultScheduleRequestDTO consultScheduleRequestDTO);
}

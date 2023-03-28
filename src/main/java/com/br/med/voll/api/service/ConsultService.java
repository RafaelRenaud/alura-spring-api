package com.br.med.voll.api.service;

import com.br.med.voll.api.model.dto.consult.ConsultScheduleRequestDTO;
import com.br.med.voll.api.model.dto.consult.ConsultScheduleResponseDTO;

public interface ConsultService {

    public ConsultScheduleResponseDTO schedule(ConsultScheduleRequestDTO consultScheduleRequestDTO);
}

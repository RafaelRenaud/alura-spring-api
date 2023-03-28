package com.br.med.voll.api.controller;

import com.br.med.voll.api.model.dto.consult.ConsultScheduleRequestDTO;
import com.br.med.voll.api.model.dto.consult.ConsultScheduleResponseDTO;
import com.br.med.voll.api.service.impl.ConsultServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("consults")
public class ConsultController {

    @Autowired
    private ConsultServiceImpl consultService;

    @PostMapping
    public ResponseEntity schedule(
            @RequestBody
            @Valid
            ConsultScheduleRequestDTO consultScheduleRequestDTO,
            UriComponentsBuilder uriComponentsBuilder
    ){
        ConsultScheduleResponseDTO responseDTO = consultService.schedule(consultScheduleRequestDTO);
        URI uri = uriComponentsBuilder.path("/consults/{consult_id}").buildAndExpand(responseDTO.consultId()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }
}

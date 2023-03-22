package com.br.med.voll.api.model.dto.doctor.put;

public record UpdateDoctorAddressRequestDTO (
     String street,
     String neighborhood,
     String zipcode,
     String city,
     String uf,
     String number,
     String complement
){}

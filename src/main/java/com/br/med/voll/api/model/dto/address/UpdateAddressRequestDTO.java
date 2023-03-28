package com.br.med.voll.api.model.dto.address;

public record UpdateAddressRequestDTO(
     String street,
     String neighborhood,
     String zipcode,
     String city,
     String uf,
     String number,
     String complement
){}

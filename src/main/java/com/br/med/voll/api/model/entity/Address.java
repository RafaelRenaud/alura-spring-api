package com.br.med.voll.api.model.entity;

import com.br.med.voll.api.model.dto.address.CreateAddressRequestDTO;
import com.br.med.voll.api.model.dto.address.UpdateAddressRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String neighborhood;
    private String zipcode;
    private String city;
    private String uf;

    @Column(name = "ADDRESS_NUMBER")
    private String addressNumber;

    private String complement;

    public Address(CreateAddressRequestDTO createAddressRequestDTO){
        this.street = createAddressRequestDTO.street();
        this.neighborhood = createAddressRequestDTO.neighborhood();
        this.zipcode = createAddressRequestDTO.zipcode();
        this.city = createAddressRequestDTO.city();
        this.uf = createAddressRequestDTO.uf();
        this.addressNumber = createAddressRequestDTO.number();
        this.complement = createAddressRequestDTO.complement();
    }

    public void updateAddress(UpdateAddressRequestDTO address){
        if(address.street() != null){
            this.street = address.street();
        }if(address.neighborhood() != null){
            this.neighborhood = address.neighborhood();
        }if(address.zipcode() != null){
            this.zipcode = address.zipcode();
        }if(address.city() != null){
            this.city = address.city();
        }if(address.uf() != null){
            this.uf = address.uf();
        }if(address.number() != null){
            this.addressNumber = address.number();
        }if(address.complement() != null){
            this.complement = address.complement();
        }
    }
}

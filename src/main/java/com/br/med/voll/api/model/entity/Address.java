package com.br.med.voll.api.model.entity;

import com.br.med.voll.api.model.dto.doctors.post.CreateDoctorAddressRequestDTO;
import com.br.med.voll.api.model.dto.doctors.put.UpdateDoctorAddressRequestDTO;
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

    public Address(CreateDoctorAddressRequestDTO createDoctorAddressRequestDTO){
        this.street = createDoctorAddressRequestDTO.street();
        this.neighborhood = createDoctorAddressRequestDTO.neighborhood();
        this.zipcode = createDoctorAddressRequestDTO.zipcode();
        this.city = createDoctorAddressRequestDTO.city();
        this.uf = createDoctorAddressRequestDTO.uf();
        this.addressNumber = createDoctorAddressRequestDTO.number();
        this.complement = createDoctorAddressRequestDTO.complement();
    }

    public void updateDoctorAddress(UpdateDoctorAddressRequestDTO address){
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

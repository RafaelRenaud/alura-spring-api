package com.br.med.voll.api.model.entity;

import com.br.med.voll.api.model.dto.doctor.post.CreateDoctorRequestDTO;
import com.br.med.voll.api.model.dto.doctor.put.UpdateDoctorRequestDTO;
import com.br.med.voll.api.model.type.Speciality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Doctor")
@Table(name = "doctors")
@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Embedded
    private Address address;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    public Doctor(CreateDoctorRequestDTO createDoctorRequestDTO){
        this.name = createDoctorRequestDTO.name();
        this.email = createDoctorRequestDTO.email();
        this.crm = createDoctorRequestDTO.crm();
        this.speciality = createDoctorRequestDTO.speciality();
        this.address = new Address(createDoctorRequestDTO.address());
        this.phoneNumber = createDoctorRequestDTO.phone();
        this.isActive = Boolean.TRUE;
    }

    public void updateDoctorInformations(UpdateDoctorRequestDTO informations){
        if(informations.name() != null){
            this.name = informations.name();
        }

        if(informations.phone() != null){
            this.phoneNumber = informations.phone();
        }

        if(informations.address() != null){
            this.address.updateDoctorAddress(informations.address());
        }
    }

    public Boolean inactiveDoctor(){
        this.isActive = Boolean.FALSE;
        return Boolean.TRUE;
    }

}

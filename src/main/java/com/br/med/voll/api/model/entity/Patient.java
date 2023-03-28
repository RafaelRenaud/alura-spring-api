package com.br.med.voll.api.model.entity;

import com.br.med.voll.api.model.dto.patient.post.CreatePatientRequestDTO;
import com.br.med.voll.api.model.dto.patient.put.UpdatePatientRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "PATIENTS")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    private String document;

    @Embedded
    private Address address;

    private Boolean isActive;

    public Patient(CreatePatientRequestDTO data) {
        this.isActive = true;
        this.name = data.name();
        this.email = data.email();
        this.phoneNumber = data.phone();
        this.document = data.cpf();
        this.address = new Address(data.address());
    }

    public void updatePatient(UpdatePatientRequestDTO data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.phone() != null) {
            this.phoneNumber = data.phone();
        }
        if (data.address() != null) {
            this.address.updateAddress(data.address());
        }

    }

    public void inactive() {
        this.isActive = false;
    }
}

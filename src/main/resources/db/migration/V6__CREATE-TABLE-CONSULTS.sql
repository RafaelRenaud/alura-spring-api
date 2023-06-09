CREATE TABLE CONSULTS(
	ID SERIAL NOT NULL PRIMARY KEY,
	DOCTOR_ID BIGINT NOT NULL,
    PATIENT_ID BIGINT NOT NULL,
    CONSULT_DATE TIMESTAMP NOT NULL,
    FOREIGN KEY(DOCTOR_ID) REFERENCES DOCTORS(ID),
    FOREIGN KEY(PATIENT_ID) REFERENCES PATIENTS(ID)
);
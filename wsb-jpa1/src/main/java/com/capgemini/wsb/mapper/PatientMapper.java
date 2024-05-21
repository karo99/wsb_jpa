package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.persistence.entity.PatientEntity;

public final class PatientMapper {
    public static PatientTO mapToTO(final PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }

        final PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setNewPatient(patientEntity.isNewPatient());
        patientTO.setVisits(VisitsMapper.mapToTO(patientEntity.getVisitEntities()));
        patientTO.setAddress(AddressMapper.mapToTO(patientEntity.getAddressEntity()));
        return patientTO;
    }

    public static PatientEntity mapToEntity(final PatientTO patientTO) {
        if(patientTO == null) {
            return null;
        }

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setNewPatient(patientTO.getNewPatient());
        patientEntity.setVisitEntities(VisitsMapper.mapToEntity(patientTO.getVisits()));
        patientEntity.setAddressEntity(AddressMapper.mapToEntity(patientTO.getAddress()));
        return patientEntity;
    }

}

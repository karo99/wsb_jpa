package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.PatientEntity;

import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    List<PatientEntity> findPatientByLastName(String lastName);

    PatientEntity findPatientVisitsByPatientId(long patId);

    List<PatientEntity> findPatientsByNumberOfVisits(long pNumOfVisits);

    List<PatientEntity> findNewPatients(boolean isNew);
}

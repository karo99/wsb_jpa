package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;

import java.util.List;

public interface PatientService {
    public PatientTO findById(final Long id);

    void deletePatient(PatientTO patient);

    PatientTO savePatient(PatientTO patient);

    PatientTO updatePatient(PatientTO patient);

    List<VisitTO> findPatientVisitsByPatientId(long id);
}

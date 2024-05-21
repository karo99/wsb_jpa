package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.persistence.entity.VisitEntity;

public class VisitMapper {

    public static VisitTO mapToTO(final VisitEntity visitEntity)
    {
        if (visitEntity == null)
        {
            return null;
        }
        final VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());
        visitTO.setDoctor(DoctorMapper.mapToTO(visitEntity.getDoctorEntity()));
//        visitTO.setPatient(PatientMapper.mapToTO(visitEntity.getPatientEntity()));
        visitTO.setMedicalTreatments(MedicalTreatmentsMapper.mapToTO(visitEntity.getMedicalTreatmentEntity()));
        return visitTO;
    }

    public static VisitEntity mapToEntity(final VisitTO visitTO)
    {
        if(visitTO == null)
        {
            return null;
        }
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitTO.getId());
        visitEntity.setDescription(visitTO.getDescription());
        visitEntity.setTime(visitTO.getTime());
        visitEntity.setDoctorEntity(DoctorMapper.mapToEntity(visitTO.getDoctor()));
//        visitEntity.setPatientEntity(PatientMapper.mapToEntity(visitTO.getPatient()));
        visitEntity.setMedicalTreatmentEntity(MedicalTreatmentsMapper.mapToEntity(visitTO.getMedicalTreatments()));
        return visitEntity;
    }
}

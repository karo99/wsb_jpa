package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.persistence.entity.VisitEntity;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

public final class VisitsMapper {
    public static List<VisitTO> mapToTO(final List<VisitEntity> visitEntities) {
        if (visitEntities == null) {
            return null;
        }
        return visitEntities.stream()
                .map((visitEntity -> {
                    final VisitTO visitTO = new VisitTO();
                    visitTO.setId(visitEntity.getId());
                    visitTO.setDescription(visitEntity.getDescription());
                    visitTO.setTime(visitEntity.getTime());
                    visitTO.setDoctor(DoctorMapper.mapToTO(visitEntity.getDoctorEntity()));
//                    visitTO.setPatient(PatientMapper.mapToTO(visitEntity.getPatientEntity()));
                    visitTO.setMedicalTreatments(MedicalTreatmentsMapper.mapToTO(visitEntity.getMedicalTreatmentEntity()));
                    return visitTO;
                }))
                .collect(Collectors.toList());
    }

    public static List<VisitEntity> mapToEntity(final List<VisitTO> visitTOs) {
        if(visitTOs == null) {
            return null;
        }
        return visitTOs.stream()
                .map((visitTO -> {
                    final VisitEntity visitEntity = new VisitEntity();
                    visitEntity.setId(visitTO.getId());
                    visitEntity.setDescription(visitTO.getDescription());
                    visitEntity.setTime(visitTO.getTime());
                    visitEntity.setDoctorEntity(DoctorMapper.mapToEntity(visitTO.getDoctor()));
//                    visitEntity.setPatientEntity(PatientMapper.mapToEntity(visitTO.getPatient()));
                    visitEntity.setMedicalTreatmentEntity(MedicalTreatmentsMapper.mapToEntity(visitTO.getMedicalTreatments()));
                    return visitEntity;
                }))
                .collect(Collectors.toList());
    }
}

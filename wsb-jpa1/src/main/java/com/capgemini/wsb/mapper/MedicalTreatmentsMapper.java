package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.MedicalTreatmentTO;
import com.capgemini.wsb.persistence.entity.MedicalTreatmentEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class MedicalTreatmentsMapper {
    public static List<MedicalTreatmentTO> mapToTO(final List<MedicalTreatmentEntity> medicalTreatmentEntities) {
        if (medicalTreatmentEntities == null) {
            return null;
        }
        return medicalTreatmentEntities.stream()
                .map((medicalTreatmentEntity -> {
                    final MedicalTreatmentTO medicalTreatmentTO = new MedicalTreatmentTO();
                    medicalTreatmentTO.setId(medicalTreatmentEntity.getId());
                    medicalTreatmentTO.setDescription(medicalTreatmentEntity.getDescription());
                    medicalTreatmentTO.setType((medicalTreatmentEntity.getType()));
                    return medicalTreatmentTO;
                }))
                .collect(Collectors.toList());
    }

    public static List<MedicalTreatmentEntity> mapToEntity(final List<MedicalTreatmentTO> medicalTreatmentTOs) {
        if(medicalTreatmentTOs == null) {
            return null;
        }
        return medicalTreatmentTOs.stream()
                .map((medicalTreatmentTO -> {
                    final MedicalTreatmentEntity medicalTreatmentEntity = new MedicalTreatmentEntity();
                    medicalTreatmentEntity.setId(medicalTreatmentTO.getId());
                    medicalTreatmentEntity.setDescription(medicalTreatmentTO.getDescription());
                    medicalTreatmentEntity.setType((medicalTreatmentTO.getType()));
                    return medicalTreatmentEntity;
                }))
                .collect(Collectors.toList());
    }
}

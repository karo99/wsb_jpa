package com.capgemini.wsb.service.impl;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.mapper.PatientMapper;
import com.capgemini.wsb.mapper.VisitsMapper;
import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao pPatientDao) {
        patientDao = pPatientDao;
    }

    @Override
    public PatientTO findById(Long id) {
        final PatientEntity entity = patientDao.findOne(id);
        return PatientMapper.mapToTO(entity);
    }

    @Override
    public void deletePatient(PatientTO patient) {
        PatientEntity patientEntity = PatientMapper.mapToEntity(patient);
        patientDao.delete(patientEntity.getId());
    }

    @Override
    public PatientTO savePatient(PatientTO patient) {
        PatientEntity savedPatient = patientDao.save(PatientMapper.mapToEntity(patient));
        return PatientMapper.mapToTO(savedPatient);
    }

    @Override
    public PatientTO updatePatient(PatientTO patient) {
        PatientEntity updatedPatient = patientDao.update(PatientMapper.mapToEntity(patient));
        return PatientMapper.mapToTO(updatedPatient);
    }

    @Override
    public List<VisitTO> findPatientVisitsByPatientId(long id) {
        PatientEntity patientEntity = patientDao.findPatientVisitsByPatientId(id);
        return VisitsMapper.mapToTO(patientEntity.getVisitEntities());
    }
}

package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientDaoImpl  extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @Override
    public List<PatientEntity> findPatientByLastName(String lastName) {
        return entityManager.createQuery(" select pat from PatientEntity pat "+
                        " where pat.lastName like :param1", PatientEntity.class)
                .setParameter("param1", "%"+lastName+"%")
                .getResultList();
    }

    @Override
    public PatientEntity findPatientVisitsByPatientId(long patId) {
        return entityManager.createQuery(" select pat from PatientEntity pat "+
                        " join pat.visitEntities visits "+
                        " where pat.id = :param1", PatientEntity.class)
                .setParameter("param1", patId)
                .getSingleResult();

    }

    @Override
    public List<PatientEntity> findPatientsByNumberOfVisits(long pNumOfVisits) {
        return entityManager.createQuery(" select pat from PatientEntity pat "+
                        " join pat.visitEntities visits "+
                        " group by pat having count(visits) > :param1 ", PatientEntity.class)
                .setParameter("param1", pNumOfVisits)
                .getResultList();
    }

    // wcześniej utworzone pole było typu boolean, więc jedyną opcją jest szukanie pacjentów bezp. po tej wartości
    @Override
    public List<PatientEntity> findNewPatients(boolean isNew) {
        return entityManager.createQuery("select pat from PatientEntity pat " +
                        "where pat.isNewPatient = :isNew", PatientEntity.class)
                .setParameter("isNew", isNew)
                .getResultList();
    }
}

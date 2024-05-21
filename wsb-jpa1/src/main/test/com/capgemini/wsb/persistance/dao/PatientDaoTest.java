package com.capgemini.wsb.persistance.dao;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Transactional
    @Test
    public void testShouldFindPatientsByLastName() {
        // given
        // when
        List<PatientEntity> patients = patientDao.findPatientByLastName("Nowak");
        // then
        assertThat(patients.get(0).getLastName()).isEqualTo("Nowak");
    }

    @Transactional
    @Test
    public void testShouldFindPatientsByNumberOfVisits() {
        // given
        // when
        List<PatientEntity> patients = patientDao.findPatientsByNumberOfVisits(1L);
        // then
        assertThat(patients.get(0).getId()).isEqualTo(1L);
    }

    @Transactional
    @Test
    public void testShouldFindNewPatients() {
        // given
        // when
        List<PatientEntity> patients = patientDao.findNewPatients(true);
        // then
        assertThat(patients.get(0).isNewPatient()).isEqualTo(true);
    }

}

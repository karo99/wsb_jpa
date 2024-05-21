package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.AddressTO;
import com.capgemini.wsb.dto.DoctorTO;
import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.mapper.DoctorMapper;
import com.capgemini.wsb.mapper.VisitMapper;
import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.dao.VisitDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest
{
    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testShouldFindPatientById() {
        // when
        PatientTO patient = patientService.findById(1L);
        // then
        assertThat(patient.getFirstName()).isEqualTo("Tomasz");
    }

    @Transactional
    @Test
    public void testShouldRemovePatient() {
        // given
        AddressTO address = new AddressTO();
        address.setAddressLine1("line1");
        address.setAddressLine2("line2");
        address.setCity("City1");
        address.setPostalCode("66-666");


        List<VisitTO> visits = new ArrayList<VisitTO>();

        PatientTO patient = new PatientTO();
        patient.setNewPatient(true);
        patient.setFirstName("Jan");
        patient.setLastName("Sokół");
        patient.setPatientNumber("1234");
        patient.setDateOfBirth(LocalDate.now());

        patient.setTelephoneNumber("09142810");
        patient.setAddress(address);
        patient.setVisits(visits);

        final PatientTO saved = patientService.savePatient(patient);

        DoctorTO doctor = new DoctorTO();
        doctor.setFirstName("Tomasz");
        doctor.setLastName("Lubicz");
        doctor.setTelephoneNumber("asd");
        doctor.setEmail("asd");
        doctor.setDoctorNumber("asd");
        doctor.setSpecialization(Specialization.DERMATOLOGIST);

        DoctorEntity savedDoctor = doctorDao.save(DoctorMapper.mapToEntity(doctor));

        VisitTO firstVisit = new VisitTO();
        firstVisit.setDescription("test desc");
        firstVisit.setTime(LocalDateTime.now());
        firstVisit.setPatient(patientService.findById(saved.getId()));
        firstVisit.setDoctor(DoctorMapper.mapToTO(doctorDao.findOne(savedDoctor.getId())));
        visitDao.save(VisitMapper.mapToEntity(firstVisit));

        // when
        patientService.deletePatient(saved);

        // then
        PatientTO deletedPatient = patientService.findById(saved.getId());
        assertThat(deletedPatient).isNull();

        assertTrue(saved.getVisits().isEmpty());

        assertThat(doctorDao.findOne(savedDoctor.getId()).getLastName()).isEqualTo("Lubicz");
    }

    @Transactional
    @Test
    public void testShouldCheckPatient() {
        // given
        AddressTO address = new AddressTO();
        address.setAddressLine1("line1");
        address.setAddressLine2("line2");
        address.setCity("City1");
        address.setPostalCode("66-666");

        PatientTO patient = new PatientTO();
        patient.setNewPatient(true);
        patient.setFirstName("Jan");
        patient.setLastName("Sokół");
        patient.setPatientNumber("1234");
        patient.setDateOfBirth(LocalDate.now());

        patient.setTelephoneNumber("09142810");
        patient.setAddress(address);

        // when
        final PatientTO saved = patientService.savePatient(patient);

        // then
        assertThat(patientService.findById(saved.getId()).getNewPatient()).isEqualTo(true);
    }

    @Transactional
    @Test
    public void testShouldFindPatientVisitsByPatientId() {
        // given
        DoctorTO doctor = new DoctorTO();
        doctor.setFirstName("Tomasz");
        doctor.setLastName("Lubicz");
        doctor.setTelephoneNumber("asd");
        doctor.setEmail("asd");
        doctor.setDoctorNumber("asd");
        doctor.setSpecialization(Specialization.DERMATOLOGIST);
        DoctorEntity savedDoctor = doctorDao.save(DoctorMapper.mapToEntity(doctor));

        VisitTO firstVisit = new VisitTO();
        firstVisit.setDescription("first visit");
        firstVisit.setTime(LocalDateTime.now());
        firstVisit.setPatient(patientService.findById(1L));
        firstVisit.setDoctor(DoctorMapper.mapToTO(doctorDao.findOne(savedDoctor.getId())));
        visitDao.save(VisitMapper.mapToEntity(firstVisit));

        VisitTO secondVisit = new VisitTO();
        secondVisit.setDescription("second visit");
        secondVisit.setTime(LocalDateTime.now());
        secondVisit.setPatient(patientService.findById(1L));
        secondVisit.setDoctor(DoctorMapper.mapToTO(doctorDao.findOne(savedDoctor.getId())));
        visitDao.save(VisitMapper.mapToEntity(secondVisit));

        List<VisitTO> visits = new ArrayList<>();
        visits.add(firstVisit);
        visits.add(secondVisit);

        // when
        List<VisitTO> foundVisits = patientService.findPatientVisitsByPatientId(1L);

        // then
        assertThat(foundVisits.get(0).getDescription()).isEqualTo("first visit");
    }
}

package com.capgemini.wsb.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;


	// relacja dwustronna wizyta-doktor
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCTOR_ID", nullable = false)
	private DoctorEntity doctorEntity;

	// relacja jednokierunkowa od strony wizyty
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "VISIT_ID", nullable = false)
	private List<MedicalTreatmentEntity> medicalTreatmentEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public DoctorEntity getDoctorEntity() {
		return doctorEntity;
	}

	public void setDoctorEntity(DoctorEntity doctorEntity) {
		this.doctorEntity = doctorEntity;
	}


	public List<MedicalTreatmentEntity> getMedicalTreatmentEntity() {
		return medicalTreatmentEntity;
	}

	public void setMedicalTreatmentEntity(List<MedicalTreatmentEntity> medicalTreatmentEntity) {
		this.medicalTreatmentEntity = medicalTreatmentEntity;
	}
}

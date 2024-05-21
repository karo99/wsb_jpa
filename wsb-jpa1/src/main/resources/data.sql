insert into address (id, address_line1, address_line2, city, postal_code)
values (1, 'Boczna 10', null, 'Koszalin', '62-030');
insert into address (id, address_line1, address_line2, city, postal_code)
values (2, 'Krótka 3', null, 'Wrocław', '51-030');

insert into doctor (id, doctor_number, email, first_name, last_name,
                    specialization, telephone_number)
values (1, '#1', null, 'Paweł', 'Lubicz', 'GP', '727272');

insert into patient (id, date_of_birth, email, first_name, last_name,
                     patient_number, telephone_number, is_new_patient, address_id)
values (1,'1980-01-12' , null, 'Tomasz', 'Górski', '#1', '123153', true, 2);

insert into patient (id, date_of_birth, email, first_name, last_name,
                     patient_number, telephone_number, is_new_patient, address_id)
values (2,'1983-05-10' , null, 'Anna', 'Nowak', '#2', '1231542353', true, 1);

insert into visit (id, description, time, doctor_id, patient_id)
values (1, 'first visit', '2024-03-03T10:15:30', 1, 1);

insert into visit (id, description, time, doctor_id, patient_id)
values (2, null, '2024-05-03T10:20:30', 1, 1);

insert into visit (id, description, time, doctor_id, patient_id)
values (3, null, '2024-05-05T10:20:30', 1, 2);

insert into medical_treatment (id, description, type, visit_id)
values (1, 'Take a pill', 'EKG', 1);

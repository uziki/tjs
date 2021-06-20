DELETE
FROM prescriptions;
DELETE
FROM events;
DELETE
FROM proc_or_meds;
DELETE
FROM patients;
DELETE
FROM users;

ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO users (name, email, password, role)
VALUES ('doctor1', 'doctor@gmail.com', '{noop}doctor', 'DOCTOR'),
       ('doctor2', 'doctor2@gmail.com', '{noop}doctor', 'DOCTOR'),
       ('nurse', 'nurse@gmail.com', '{noop}nurse', 'NURSE');

INSERT into patients(name, diagnosis, insurance, ill, user_id)
VALUES ('Иванов Иван', 'Broken leg', '1111', true, 1),
       ('Петров Петр', 'Cold', '2222', true, 2);

INSERT INTO proc_or_meds (name, type)
VALUES ('Aspirin', 'TYPE_MEDICINE'),
       ('Massage', 'TYPE_PROCEDURE');

INSERT INTO prescriptions (time_pattern, time_period, dose, patient_id, proc_or_meds_id, user_id)
VALUES ('1-10:50 2- 3-', '1', 2, 4, 6, 1),
       ('1- 2- 3- 4-10:37 5- 6- 7-', '1', 0, 5, 7, 2);

INSERT INTO events(patient_id, date_time, proc_or_meds_id, prescription_id, dose, status, message)
VALUES (4, '2020-06-21 10:50', 6, 8, 2, 'STATUS_PLANNED', ''),
       (5, '2020-06-24 10:37', 7, 9, 0, 'STATUS_PLANNED', '');



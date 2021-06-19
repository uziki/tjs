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
       ('Nurse', 'nurse@gmail.com', '{noop}nurse', 'NURSE'),
       ('doctor2', 'doctor2@gmail.com', '{noop}doctor', 'DOCTOR');

INSERT into patients(name, diagnosis, insurance, ill, user_id)
VALUES ('Иванов Иван', 'Broken leg', '1111', true, 1),
       ('Петров Петр', 'Cold', '2222', true, 1),
       ('Сидоров Сидр', 'Gunshot wound', '3333', true, 3);

INSERT INTO proc_or_meds (name, type)
VALUES ('Aspirin', 'TYPE_MEDICINE'),
       ('Morning exercise', 'TYPE_PROCEDURE'),
       ('Massage', 'TYPE_PROCEDURE');

INSERT INTO prescriptions (time_pattern, time_period, dose, patient_id, proc_or_meds_id, user_id)
VALUES ('1-10:50 2- 3-20:00', '1', 2, 4, 7, 1),
       ('1- 2- 3- 4-10:37 5- 6- 7-', '2', 0, 5, 8, 1),
       ('1- 2-04:00 3- 4- 5- 6- 7-', '2', 0, 6, 8, 1),
       ('1- 2- 3- 4- 5- 6-18:50 7-19:50', '1', 0, 6, 9, 3);

INSERT INTO events(patient_id, date_time, proc_or_meds_id, prescription_id, dose, status, message)
VALUES (4, '2020-06-20 10:50', 7, 10, 2, 'STATUS_PLANNED', ''),
       (4, '2020-06-20 20:00', 7, 10, 2, 'STATUS_DONE', ''),
       (5, '2020-06-24 10:37', 8, 11, 0, 'STATUS_CANCELED', 'Didnt want'),
       (5, '2020-07-01 10:37', 8, 11, 0, 'STATUS_DONE', ''),
       (6, '2020-06-22 04:00', 8, 12, 0, 'STATUS_DONE', ''),
       (6, '2020-06-29 04:00', 8, 12, 0, 'STATUS_DONE', ''),
       (6, '2020-06-20 19:50', 9, 13, 0, 'STATUS_DONE', ''),
       (6, '2020-06-26 18:50', 9, 13, 0, 'STATUS_DONE', '')


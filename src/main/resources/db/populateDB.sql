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
VALUES ('doctor1', 'doctor@gmail.com', 'doctor', 'DOCTOR'),
       ('Nurse', 'nurse@gmail.com', 'nurse', 'NURSE'),
       ('doctor2', 'doctor2@gmail.com', 'doctor', 'DOCTOR');

INSERT into patients(name, diagnosis, insurance, ill, user_id)
VALUES ('Иванов Иван', 'Перелом пупка', '1111', true, 1),
       ('Петров Петр', 'Вывих глаза', '2222', true, 1),
       ('Сидоров Сидр', 'Не жилец', '3333', true, 3);

INSERT INTO proc_or_meds (name, type)
VALUES ('Аспирин', 'TYPE_MEDICINE'),
       ('Зарядка', 'TYPE_PROCEDURE'),
       ('Массаж', 'TYPE_PROCEDURE');

INSERT INTO prescriptions (time_pattern, time_period, dose, patient_id, proc_or_meds_id, user_id)
VALUES ('1-10:10 2- 3-20:20', '5', 2, 4, 7, 1),
       ('1-02:14 2- 3- 4- 5-01:14 6- 7-01:14', '2', 0, 5, 8, 1),
       ('1-02:14 2- 3- 4- 5-01:14 6- 7-01:14', '2', 0, 6, 8, 1),
       ('1- 2-10:00 3- 4-15:15 5- 6- 7-', '3', 0, 6, 9, 3);

INSERT INTO events(patient_id, date_time, proc_or_meds_id, prescription_id, dose, status, message)
VALUES (4, '2020-05-18 16:00', 7, 10, 2, 'STATUS_PLANNED', ''),
       (6, '2020-05-19 09:00', 9, 13, 0, 'STATUS_DONE', ''),
       (5, '2020-05-21 08:00', 8, 11, 0, 'STATUS_CANCELED', 'Передумал')
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
VALUES ('Doctor', 'doctor@gmail.com', '{noop}doctor', 'ROLE_DOCTOR'),
       ('Nurse', 'nurse@gmail.com', '{noop}nurse', 'ROLE_NURSE'),
       ('Doctor2', 'doctor2@gmail.com', '{noop}doctor', 'ROLE_DOCTOR');

INSERT into patients(name, diagnosis, insurance, ill, user_id)
VALUES ('Иванов Иван', 'Перелом пупка', '1111', true, 1),
       ('Петров Петр', 'Вывих глаза', '2222', true, 1),
       ('Сидоров Сидр', 'Не жилец', '3333', true, 3);

INSERT INTO proc_or_meds (name, type)
VALUES ('Аспирин', 'TYPE_MEDICINE'),
       ('Зарядка', 'TYPE_PROCEDURE'),
       ('Массаж', 'TYPE_PROCEDURE');

INSERT INTO prescriptions (time_pattern, time_period, dose, patient_id, proc_or_meds_id)
VALUES ('утром и вечером', '5', 2, 4, 7),
       ('по четвергам и пятницам', '2', 0, 5, 8),
       ('по четвергам и пятницам', '2', 0, 6, 8),
       ('по понедельникам и воскресеньям', '3', 0, 6, 9);

DELETE
FROM prescriptions;
DELETE
FROM events;
DELETE
FROM prescriptionstype;
DELETE
FROM meds;
DELETE
FROM patients;
DELETE
FROM users;

ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO users (name, email, password, role)
VALUES ('Nurse', 'nurse@gmail.com', '{noop}nurse', 'ROLE_NURSE'),
       ('Doctor', 'doctor@gmail.com', '{noop}doctor', 'ROLE_DOCTOR');

INSERT into patients(name, diagnosis, insurance, ill, user_id)
VALUES ('Иванов Иван', 'Перелом пупка', '1111', true, 2),
       ('Петров Петр', 'Вывих глаза', '2222', true, 2),
       ('Сидоров Сидр', 'Не жилец', '3333', true, 2);

INSERT INTO meds (name)
VALUES ('Аспирин'),
       ('Зарядка'),
       ('Массаж');

INSERT INTO prescriptionstype (meds_id, type)
VALUES (6, 'TYPE_MEDICINE'),
       (7, 'TYPE_PROCEDURE'),
       (8, 'TYPE_PROCEDURE');

INSERT INTO prescriptions (time_pattern, time_period, dose, patient_id, meds_id)
VALUES ('утром и вечером', '5', 2, 3, 6),
       ('по четвергам и пятницам', '2', 0, 4, 7),
       ('по понедельникам и воскресеньям', '3', 0, 5, 8);

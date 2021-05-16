DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS prescriptions;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS proc_or_meds;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 1;

CREATE TABLE users
(
    id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name     VARCHAR NOT NULL,
    email    VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    role     VARCHAR NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE patients
(
    id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name      VARCHAR                          NOT NULL,
    diagnosis VARCHAR,
    insurance VARCHAR                          NOT NULL,
    user_id   INTEGER                          NOT NULL,
    ill       BOOL                DEFAULT TRUE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);



CREATE TABLE proc_or_meds
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL,
    type VARCHAR NOT NULL
);


CREATE TABLE prescriptions
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    time_pattern    VARCHAR NOT NULL,
    time_period     VARCHAR NOT NULL,
    patient_id      INTEGER NOT NULL,
    user_id         INTEGER NOT NULL,
    dose            INTEGER,
    proc_or_meds_id INTEGER NOT NULL,
    active          BOOLEAN DEFAULT TRUE NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patients (id) ON DELETE CASCADE,
    FOREIGN KEY (proc_or_meds_id) references proc_or_meds (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE events
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    patient_id      INTEGER   NOT NULL,
    date_time       TIMESTAMP NOT NULL,
    proc_or_meds_id INTEGER   NOT NULL,
    prescription_id INTEGER   NOT NULL,
    message         VARCHAR,
    dose            INTEGER,
    status          VARCHAR NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patients (id) ON DELETE CASCADE,
    FOREIGN KEY (prescription_id) REFERENCES prescriptions (id) ON DELETE CASCADE,
    FOREIGN KEY (proc_or_meds_id) REFERENCES proc_or_meds (id)
);


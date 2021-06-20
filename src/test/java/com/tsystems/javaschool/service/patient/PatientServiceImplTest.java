package com.tsystems.javaschool.service.patient;

import com.tsystems.javaschool.model.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.tsystems.javaschool.model.AbstractBaseEntity.START_SEQ;
import static com.tsystems.javaschool.service.TestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"), executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PatientServiceImplTest {
    @Autowired
    private PatientService service;

    @Test
    public void get() {
        Patient actual = service.get(START_SEQ + 3);
        Patient expected = getExpectedPatient1();
        assertMatch(expected, actual);
    }

    @Test
    public void getAll() {
        List<Patient> actual = service.getAll();
        assertMatchLists(actual, getExpectedPatient1(), getExpectedPatient2());
    }

    @Test
    public void update() {
        Patient updated = getUpdatedPatient();
        service.update(updated, updated.getDoctor().getId());
        assertMatch(updated, service.get(updated.getId()));
    }

    @Test
    public void create() {
        Patient expected = getNewPatient();
        Patient actual = service.create(expected, getExpectedUser1().getId());
        assertMatch(expected, actual);
    }
}
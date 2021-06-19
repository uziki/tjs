package com.tsystems.javaschool.service.patient;

import com.tsystems.javaschool.model.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static com.tsystems.javaschool.service.TestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class PatientServiceImplTest {
    @Autowired
    private PatientService service;

    @Test
    public void get() {
        Patient actual = service.get(START_ID + 3);
        Patient expected = patient1;
        expected.setPrescriptions(Arrays.asList(prescription));
        assertMatch(expected, actual);
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void create() {
    }
}
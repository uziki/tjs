package com.tsystems.javaschool.service.prescription;

import com.tsystems.javaschool.model.Prescription;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

import static com.tsystems.javaschool.model.AbstractBaseEntity.START_SEQ;
import static com.tsystems.javaschool.service.TestData.*;
import static org.junit.Assert.assertThrows;

@ContextConfiguration({
        "classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"), executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PrescriptionServiceImplTest {
    @Autowired
    private PrescriptionService service;

    @Test
    public void get() {
        Prescription actual = service.get(START_SEQ + 7);
        Prescription expected = getExpectedPrescription1();
        assertMatch(expected, actual);
    }

    @Test
    public void getWithId() {
        Prescription actual = service.getWithId(START_SEQ + 7, START_SEQ + 3);
        Prescription expected = getExpectedPrescription1();
        assertMatch(expected, actual);
    }

    @Test
    public void getWithIdNotFound() {
        assertThrows(NoResultException.class, () -> service.getWithId(START_SEQ + 7, START_SEQ + 300));
    }

    @Test
    public void getAllWithId() {
        List<Prescription> actual = service.getAllWithId(START_SEQ + 4);
        assertMatchLists(actual, getExpectedPrescription2());
    }

    @Test
    public void getAllWithIdNotFound() {
        List<Prescription> actual = service.getAllWithId(START_SEQ + 300);
        assertMatch(new ArrayList<>(), actual);
    }

    @Test
    public void update() {

    }

    @Test
    public void create() {
        Prescription expected = getNewPrescription();
        Prescription actual = service.create(expected);
        assertMatch(expected, actual);
    }
}
package com.tsystems.javaschool.service.procedureOrMedicineService;

import com.tsystems.javaschool.model.PrescriptionType;
import com.tsystems.javaschool.model.ProcedureOrMedicine;
import com.tsystems.javaschool.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.NoResultException;

import static com.tsystems.javaschool.service.TestData.assertMatch;
import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class ProcedureOrMedicineServiceImplTest {

    @Autowired
    private ProcedureOrMedicineService service;

    @Test
    public void getByNameAndType() {
        ProcedureOrMedicine expected = new ProcedureOrMedicine("Massage", PrescriptionType.TYPE_PROCEDURE);
        ProcedureOrMedicine actual = service.getByNameAndType("Massage", "TYPE_PROCEDURE");
        expected.setId(actual.getId());
        assertMatch(expected, actual);
    }

    @Test
    public void getByNameAndTypeNotFound() {
        assertThrows(NoResultException.class, () -> service.getByNameAndType("a", "TYPE_MEDICINE"));
    }

    @Test
    public void create() {
        ProcedureOrMedicine expected = new ProcedureOrMedicine();
        expected.setName("Aspirin");
        expected.setPrescriptionType(PrescriptionType.TYPE_MEDICINE);
        ProcedureOrMedicine actual = service.create(expected);//assertEquals(expected, actual);
        assertMatch(expected, actual);
    }

    @Test
    public void createWithNameAndType() {
        ProcedureOrMedicine pom1 = service.createWithNameAndType("Test", "TYPE_MEDICINE");
        ProcedureOrMedicine pom2 = service.createWithNameAndType("Test", "TYPE_MEDICINE");
        assertMatch(pom1, pom2);
    }
}
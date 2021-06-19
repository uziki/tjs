package com.tsystems.javaschool.service.user;

import com.tsystems.javaschool.model.User;
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
public class UserServiceImplTest {
    @Autowired
    private UserService service;

    @Test
    public void get() {
        User actual = service.get(START_ID);
        User expected = user1;
        user1.setPatients(Arrays.asList(patient1, patient2, patient3));
        assertMatchUsers(expected, actual);
    }

    @Test
    public void create() {
        User newUser = getNewUser();
        User actual = service.create(newUser);
        newUser.setId(actual.getId());
        assertMatchUsers(newUser, actual);
    }
}
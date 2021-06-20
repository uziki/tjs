package com.tsystems.javaschool.service.user;

import com.tsystems.javaschool.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static com.tsystems.javaschool.model.AbstractBaseEntity.START_SEQ;
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
        User actual = service.get(START_SEQ);
        User expected = getExpectedUser1();
        assertMatch(expected, actual);
    }

    @Test
    public void create() {
        User newUser = getNewUser();
        User actual = service.create(newUser);
        newUser.setId(actual.getId());
        assertMatch(newUser, actual);
    }
}
package com.tsystems.javaschool.service.event;

import com.tsystems.javaschool.model.Event;
import com.tsystems.javaschool.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static com.tsystems.javaschool.model.AbstractBaseEntity.START_SEQ;
import static com.tsystems.javaschool.service.TestData.*;
import static org.junit.Assert.assertThrows;

@ContextConfiguration({
        "classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class EventServiceImplTest {
    @Autowired
    private EventService service;
    private static final String MQ_MESSAGE = "New data";

    @Test
    public void get() {
        Event actual = service.get(START_SEQ + 9);
        Event expected = getExpectedEvent1();
        assertMatch(expected, actual);
    }

    @Test
    public void delete() {
        service.delete(START_SEQ + 9);
        assertThrows(NotFoundException.class, () -> service.delete(START_SEQ + 9));
    }

    @Test
    public void deleteNotFound() {
        service.delete(START_SEQ + 9);
        assertThrows(NotFoundException.class, () -> service.delete(START_SEQ + 300));
    }

    @Test
    public void getAll() {
        List<Event> actual = service.getAll();
        assertMatchLists(actual, getExpectedEvent1(), getExpectedEvent2());
    }

    @Test
    public void getByPrescriptionId() {
        List<Event> actual = service.getByPrescriptionId(START_SEQ + 7);
        List<Event> expected = Arrays.asList(getExpectedEvent1());
        assertMatch(expected, actual);
    }

    @Test
    public void update() {
        Event updated = getUpdatedEvent();
        service.update(updated);
        assertMatchIgnoringLDT(updated, service.get(updated.getId()));
    }

    @Test
    public void create() {
        Event expected = getNewEvent();
        Event actual = service.create(expected);
        assertMatch(expected, actual);
    }

    @Test
    public void getBetweenDates2() {
        List<Event> actual = service.getBetweenDates(LocalDateTime.of(2020, 06, 21, 0, 0, 00),
                LocalDateTime.of(2020, 06, 25, 0, 0, 00));
        List<Event> expected = Arrays.asList(getExpectedEvent1(), getExpectedEvent2());
        assertMatch(expected, actual);
    }

    @Test
    public void getBetweenDates1() {
        List<Event> actual = service.getBetweenDates(LocalDateTime.of(2020, 06, 21, 0, 0, 00),
                LocalDateTime.of(2020, 06, 22, 0, 0, 00));
        List<Event> expected = Arrays.asList(getExpectedEvent1());
        assertMatch(expected, actual);
    }

    @Test
    public void getBetweenDates() {
        List<Event> actual = service.getBetweenDates(LocalDateTime.of(2019, 06, 21, 0, 0, 00),
                LocalDateTime.of(2019, 06, 25, 0, 0, 00));
        assertMatch(new ArrayList<>(), actual);
    }

    @Test
    public void findByName() {
        List<Event> actual = service.findByName("Иванов");
        List<Event> expected = Arrays.asList(getExpectedEvent1());
        assertMatch(expected, actual);
    }

    @Test
    public void findByNameNotFound() {
        List<Event> actual = service.findByName("Name");
        assertMatch(new ArrayList<>(), actual);
    }

    /*@Test
    public void notifyMQ() {
        String actual = "";
        service.notifyMQ();
        Properties props = new Properties();
        props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.put("java.naming.provider.url", "tcp://localhost:61616");
        props.put("queue.js-queue", "events_queue");
        props.put("connectionFactoryNames", "queueCF");

        try {
            Context context = new InitialContext(props);
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("queueCF");
            Queue queue = (Queue) context.lookup("js-queue");

            QueueConnection connection = connectionFactory.createQueueConnection();
            connection.start();

            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            QueueReceiver receiver = session.createReceiver(queue);
            TextMessage message = (TextMessage) receiver.receive();

            actual = message.getText();
            receiver.close();
            session.close();
            connection.close();
        } catch (NamingException | JMSException e) {
            System.out.println("No connection to MQ");
        }
        assertMatch(MQ_MESSAGE, actual);
    }*/
}
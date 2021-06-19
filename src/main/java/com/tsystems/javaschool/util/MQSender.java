package com.tsystems.javaschool.util;

import com.tsystems.javaschool.util.exception.NoConnectionToMQException;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class MQSender {
    private static final String JAVA_NAMING_FACTORY_INITIAL = "java.naming.factory.initial";
    private static final String INITIAL_CONTEXT_FACTORY = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
    private static final String NAMING_PROVIDER_URL = "java.naming.provider.url";
    private static final String PROVIDER_URL = "tcp://localhost:61616";
    private static final String QUEUE_JS_QUEUE = "queue.js-queue";
    private static final String QUEUE_NAME = "events_queue";
    private static final String CONNECTION_FACTORY_NAMES = "connectionFactoryNames";
    private static final String QUEUECF = "queueCF";
    private static final String JS_QUEUE = "js-queue";

    public static void sendMessage() {
        Properties props = new Properties();
        props.put(JAVA_NAMING_FACTORY_INITIAL, INITIAL_CONTEXT_FACTORY);
        props.put(NAMING_PROVIDER_URL, PROVIDER_URL);
        props.put(QUEUE_JS_QUEUE, QUEUE_NAME);
        props.put(CONNECTION_FACTORY_NAMES, QUEUECF);

        try {
            Context context = new InitialContext(props);
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup(QUEUECF);
            Queue queue = (Queue) context.lookup(JS_QUEUE);

            QueueConnection connection = connectionFactory.createQueueConnection();
            connection.start();

            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            QueueSender sender = session.createSender(queue);
            TextMessage message = session.createTextMessage("New data");

            sender.send(message);

            sender.close();
            session.close();
            connection.close();
        } catch (NamingException | JMSException e) {
            throw new NoConnectionToMQException("No connection to MQ");
        }
    }
}

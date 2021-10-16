package hello;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProducer {
    String brokerURL = "tcp://127.0.0.1:61616";
    String USER_NAME= "admin";
    String PASSWORD= "admin";

    void MessageSender(){
        try {
//            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
//                    USER_NAME, PASSWORD, brokerURL);
            ActiveMQConnectionFactory connectionFactory =
                    new ActiveMQConnectionFactory(brokerURL);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("TEST.FOO");

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode();

            TextMessage message = session.createTextMessage(text);

            // Tell the producer to send the message
            System.out.println("Sent message: "+ text + " " + message.hashCode() + " : " + Thread.currentThread().getName());
            producer.send(message);

            // Clean up
            session.close();
            connection.close();
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }

    }


}

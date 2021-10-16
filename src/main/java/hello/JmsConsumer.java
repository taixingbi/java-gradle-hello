package hello;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsConsumer {
    String brokerURL = "tcp://127.0.0.1:61616";
    String USER_NAME= "admin";
    String PASSWORD= "admin";

    void MessageReceiver(){
        try {
//            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
//                    USER_NAME, PASSWORD, brokerURL);
            ActiveMQConnectionFactory connectionFactory =
                    new ActiveMQConnectionFactory(brokerURL);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("TEST.FOO");

            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive(1000);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Received message: " + text);
            } else {
                System.out.println("Received: " + message);
            }

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

package hello;


public class App {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("----------app start----------");
        JmsProducer jmsProducer = new JmsProducer();
        JmsConsumer jmsConsumer = new JmsConsumer();

        jmsProducer.MessageSender();

        Thread.sleep(1000);

        jmsConsumer.MessageReceiver();

        System.out.println("----------app end----------");
    }


}

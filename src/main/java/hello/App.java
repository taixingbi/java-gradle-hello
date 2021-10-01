package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(App.class);

        System.out.println("----------app----------");
        logger.debug("Hello World");
        System.out.println("----------app end----------");
    }

}

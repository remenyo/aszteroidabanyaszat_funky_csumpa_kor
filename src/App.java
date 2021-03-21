package src;

import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class App {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static FileHandler fh = null;
    private static ConsoleHandler ch = null;

    public static void main(String[] args) throws Exception {

        fh = new FileHandler("loggerExample.log", false);
        ch = new ConsoleHandler();
        ch.setFormatter(new SimpleFormatter());
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
        logger.addHandler(ch);
        logger.setLevel(Level.ALL);

        RandomUtils r = new RandomUtils();
        System.out.println("Hello, World!");

        // randomIntHatarokKozott.test
        for (int i = 0; i < 500; i++) {
            System.out.println(r.randomIntHatarokKozott(-22, -3));
        }

        // randomBooleanValoszinuseggel.test
        for (float i = 0; i < 1.1; i += 0.1) { // 0.0 -> 1.0
            float t = 0;
            float f = 0;
            for (int j = 0; j < 1000; j++) {
                if (r.randomBooleanValoszinuseggel(i, 7))
                    t++;
                else
                    f++;
            }
            System.out.println("True/False expected " + i + ", actual " + t / 1000 + "\t(" + +t + "/" + f + ")");
            t = 0;
            f = 0;
        }
    }
}

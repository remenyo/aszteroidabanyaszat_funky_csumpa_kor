package src;

import java.util.logging.FileHandler;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class CsumpaLogger {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static FileHandler fh = null;
    private static ConsoleHandler ch = null;
    static boolean already_ran = false;

    // TODO add log fnuction, add Level constants

    static void init() {
        if (already_ran) {
            System.out.println("CSUMPALOGGER WAS INITIALIZED MORE THAN ONCE... DID NOTHING");
            return;
        }
        try {
            fh = new FileHandler("loggerExample.log", false);
        } catch (IOException e) {
            System.out.println("FILEHANDLER COULD NOT INITIALIZE LOG FILE");
        }
        ch = new ConsoleHandler();
        ch.setFormatter(new SimpleFormatter());
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
        logger.addHandler(ch);
        logger.setLevel(Level.ALL);
        already_ran = true;
    }

}

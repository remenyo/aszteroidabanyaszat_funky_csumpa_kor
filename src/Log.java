package src;

// még nincs kész
// TODO opcionális verbose parameter, ne kelljen new sem...

public class Log {

    // https://stackoverflow.com/a/4065546
    Log(String message, boolean verbose) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];// magic, it's used for debug. Anyway...
        if (verbose)
            System.out.println("[LOG] " + e.getFileName() + ":" + e.getLineNumber() + " (" + e.getClassName() + ") "
                    + e.getMethodName() + "() : " + message);
        else
            System.out.println(e.getClassName() + " " + e.getMethodName() + ": " + message);

    }
}

/*
 * import java.util.logging.FileHandler; import java.io.IOException; import
 * java.util.logging.ConsoleHandler; import java.util.logging.Logger; import
 * java.util.logging.Level; import java.util.logging.SimpleFormatter;
 * 
 * public class Log { private final static Logger logger =
 * Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); private static FileHandler fh =
 * null; private static ConsoleHandler ch = null; static boolean already_ran =
 * false;
 * 
 * public static Level DEBUG = Level.CONFIG; public static Level INFO =
 * Level.INFO; public static Level WARNING = Level.WARNING; public static Level
 * ERROR = Level.SEVERE;
 * 
 * // TODO add log function, add Level constants
 * 
 * static void init() { if (already_ran) { System.out.
 * println("CSUMPALOGGER WAS INITIALIZED MORE THAN ONCE... DID NOTHING");
 * return; } try { fh = new FileHandler("loggerExample.log", false); } catch
 * (IOException e) {
 * System.out.println("FILEHANDLER COULD NOT INITIALIZE LOG FILE"); } ch = new
 * ConsoleHandler(); ch.setFormatter(new SimpleFormatter()); fh.setFormatter(new
 * SimpleFormatter()); logger.addHandler(fh); logger.addHandler(ch);
 * logger.setLevel(Level.ALL); already_ran = true; }
 * 
 * }
 */
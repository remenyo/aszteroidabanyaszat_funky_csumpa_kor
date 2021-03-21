package src;

public class Log {

    // https://stackoverflow.com/a/4065546 az alap innen jött

    private static StackTraceElement getCaller() {
        return Thread.currentThread().getStackTrace()[4]; // 🪄 magic
    }

    private static String verboseLogBuilder(String level, StackTraceElement e) {
        return "[" + level + "] " + e.getFileName() + ":" + e.getLineNumber() + " (" + e.getClassName() + ") "
                + e.getMethodName() + "() : ";
    }

    private static String simpleLogBuilder(String level, StackTraceElement e) {
        return "[" + level + "] " + e.getClassName() + " " + e.getMethodName() + "() : ";
    }

    private static void log(String level, String message, boolean verbose) {
        if (verbose)
            System.out.println(verboseLogBuilder(level, getCaller()) + message);
        else
            System.out.println(simpleLogBuilder(level, getCaller()) + message);
    }

    static void error(String message) {
        log("ERROR", message, false);
    }

    static void error(String message, boolean verbose) {
        log("ERROR", message, verbose);
    }

    static void warn(String message) {
        log("WARN", message, false);
    }

    static void warn(String message, boolean verbose) {
        log("WARN", message, verbose);
    }

    static void info(String message) {
        log("INFO", message, false);
    }

    static void info(String message, boolean verbose) {
        log("INFO", message, verbose);
    }

    static void debug(String message) {
        log("DEBUG", message, false);
    }

    static void debug(String message, boolean verbose) {
        log("DEBUG", message, verbose);
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
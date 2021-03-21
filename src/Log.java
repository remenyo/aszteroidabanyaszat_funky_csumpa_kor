package src;

public class Log {

    // https://stackoverflow.com/a/4065546 az alap innen jött

    private static StackTraceElement getCaller() {
        return Thread.currentThread().getStackTrace()[4]; // ?? magic
    }

    private static String verboseLogBuilder(String level, StackTraceElement e) {
        return "[" + level + "] " + e.toString() + ": ";
    }

    private static String simpleLogBuilder(String level, StackTraceElement e) {
        return "[" + level + RESET + "] " + e.getClassName() + "." + e.getMethodName() + ": ";
    }

    private static void log(String level, String message, boolean verbose) {
        if (verbose)
            System.out.println(verboseLogBuilder(level, getCaller()) + message);
        else
            System.out.println(simpleLogBuilder(level, getCaller()) + message);
    }

    static void error(String message) {
        log(RED + "ERROR", message, false);
    }

    static void error(String message, boolean verbose) {
        log(RED + "ERROR", message, verbose);
    }

    static void warn(String message) {
        log(YELLOW + "WARN", message, false);
    }

    static void warn(String message, boolean verbose) {
        log(YELLOW + "WARN", message, verbose);
    }

    static void info(String message) {
        log(BLUE + "INFO", message, false);
    }

    static void info(String message, boolean verbose) {
        log(BLUE + "INFO", message, verbose);
    }

    static void debug(String message) {
        log(PURPLE + "DEBUG", message, false);
    }

    static void debug(String message, boolean verbose) {
        log(PURPLE + "DEBUG", message, verbose);
    }

    // Reset
    public static final String RESET = "\033[0m"; // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m"; // BLACK
    public static final String RED = "\033[0;31m"; // RED
    public static final String GREEN = "\033[0;32m"; // GREEN
    public static final String YELLOW = "\033[0;33m"; // YELLOW
    public static final String BLUE = "\033[0;34m"; // BLUE
    public static final String PURPLE = "\033[0;35m"; // PURPLE
    public static final String CYAN = "\033[0;36m"; // CYAN
    public static final String WHITE = "\033[0;37m"; // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m"; // BLACK
    public static final String RED_BOLD = "\033[1;31m"; // RED
    public static final String GREEN_BOLD = "\033[1;32m"; // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m"; // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m"; // CYAN
    public static final String WHITE_BOLD = "\033[1;37m"; // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m"; // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m"; // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m"; // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m"; // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m"; // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m"; // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m"; // BLACK
    public static final String RED_BACKGROUND = "\033[41m"; // RED
    public static final String GREEN_BACKGROUND = "\033[42m"; // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m"; // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m"; // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m"; // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m"; // BLACK
    public static final String RED_BRIGHT = "\033[0;91m"; // RED
    public static final String GREEN_BRIGHT = "\033[0;92m"; // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m"; // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m"; // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m"; // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m"; // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m"; // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m"; // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m"; // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m"; // WHITE

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
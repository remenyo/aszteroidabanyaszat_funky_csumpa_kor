package src;

public class Log {

    public static final Log INSTANCE = new Log();

    // a szinek innen vannak: https://stackoverflow.com/a/45444716
    public static String RESET = "\033[0m";
    public static String RED = "\033[0;31m"; // RED
    public static String YELLOW = "\033[0;33m"; // YELLOW
    public static String BLUE = "\033[0;34m"; // BLUE
    public static String PURPLE = "\033[0;35m"; // PURPLE
    public static String BLACK = "\033[0;30m"; // BLACK
    public static String GREEN = "\033[0;32m"; // GREEN
    public static String CYAN = "\033[0;36m"; // CYAN
    public static String WHITE_BG = "\033[47m"; // WHITE

    private Log() {
        if (!App.COLOR_IN_TERMINAL) {
            // a szinek innen vannak: https://stackoverflow.com/a/45444716
            RESET = "";
            RED = ""; // RED
            YELLOW = ""; // YELLOW
            BLUE = ""; // BLUE
            PURPLE = ""; // PURPLE
            BLACK = ""; // BLACK
            GREEN = ""; // GREEN
            CYAN = ""; // CYAN
            WHITE_BG = ""; // WHITE
        }
    }

    private static StackTraceElement getCaller(int offset) {
        // https://stackoverflow.com/a/4065546 az alap innen jött
        return Thread.currentThread().getStackTrace()[4 + offset]; // ?? magic
    }

    private static String verboseLogBuilder(String level, StackTraceElement e) {
        return "[" + level + "] " + e.toString();
    }

    private static String simpleLogBuilder(String level, StackTraceElement e) {
        return "[" + level + RESET + "] " + e.getClassName() + "." + e.getMethodName();
    }

    private static void log(String level, String message, boolean verbose, int magic_stack_pointer) {
        if (verbose)
            System.out.println(verboseLogBuilder(level, getCaller(magic_stack_pointer))
                    + (message.equals("") ? "" : ": ") + message);
        else
            System.out.println(simpleLogBuilder(level, getCaller(magic_stack_pointer))
                    + (message.equals("") ? "" : ": ") + message);
    }

    static void error(String message) {
        error(message, 0, 1);
    }

    static void error(String message, int... options) {
        if (App.LOG_LEVEL > 0)
            log(RED + "ERROR", message, options[0] == 1, options[1]);
    }

    static void warn(String message) {
        warn(message, 0, 1);
    }

    static void warn(String message, int... options) {
        if (App.LOG_LEVEL > 1)
            log(YELLOW + "WARN", message, options[0] == 1, options[1]);
    }

    static void info(String message) {
        info(message, 0, 1);
    }

    static void info(String message, int... options) {
        if (App.LOG_LEVEL > 2)
            log(BLUE + "INFO", message, options[0] == 1, options[1]);
    }

    static void debug(String message) {
        debug(message, 0, 1);
    }

    static void debug(String message, int... options) {
        if (App.LOG_LEVEL > 3)
            log(PURPLE + "DEBUG", message, options[0] == 1, options[1]);
    }

    static void call() {
        if (App.LOG_FUNCTION_CALLS)
            log(GREEN + "CALL", "", false, 0);
    }

    static void ctor() {
        if (App.LOG_CONSTRUCTORS)
            log(CYAN + "CTOR", "", false, 0);
    }
}
package src;

public class Log {

    private Log() {
    }

    private static StackTraceElement getCaller(int offset) {
        // https://stackoverflow.com/a/4065546 az alap innen j�tt
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
        if (Jatek.LOG_LEVEL > 0)
            log(RED + "ERROR", message, options[0] == 1, options[1]);
    }

    static void warn(String message) {
        log(YELLOW + "WARN", message, false, 0);
    }

    static void warn(String message, int... options) {
        if (Jatek.LOG_LEVEL > 1)
            log(YELLOW + "WARN", message, options[0] == 1, options[1]);
    }

    static void info(String message) {
        info(message, 0, 1);
    }

    static void info(String message, int... options) {
        if (Jatek.LOG_LEVEL > 2)
            log(BLUE + "INFO", message, options[0] == 1, options[1]);
    }

    static void debug(String message) {
        debug(message, 0, 1);
    }

    static void debug(String message, int... options) {
        if (Jatek.LOG_LEVEL > 3)
            log(PURPLE + "DEBUG", message, options[0] == 1, options[1]);
    }

    static void call() {
        if (Jatek.LOG_FUNCTION_CALLS)
            log(GREEN + "CALL", "", false, 0);
    }

    static void ctor() {
        if (Jatek.LOG_CONSTRUCTORS)
            log(CYAN + "CTOR", "", false, 0);
    }

    // a szinek innen vannak: https://stackoverflow.com/a/45444716

    // Reset
    public static final String RESET = "\033[0m"; // Text Reset

    // Regular Colors
    private static final String RED = "\033[0;31m"; // RED
    private static final String YELLOW = "\033[0;33m"; // YELLOW
    private static final String BLUE = "\033[0;34m"; // BLUE
    private static final String PURPLE = "\033[0;35m"; // PURPLE

    // private static final String BLACK = "\033[0;30m"; // BLACK
    private static final String GREEN = "\033[0;32m"; // GREEN
    private static final String CYAN = "\033[0;36m"; // CYAN
    // private static final String WHITE = "\033[0;37m"; // WHITE
    /*
     * // Bold private static final String BLACK_BOLD = "\033[1;30m"; // BLACK
     * private static final String RED_BOLD = "\033[1;31m"; // RED private static
     * final String GREEN_BOLD = "\033[1;32m"; // GREEN private static final String
     * YELLOW_BOLD = "\033[1;33m"; // YELLOW private static final String BLUE_BOLD =
     * "\033[1;34m"; // BLUE private static final String PURPLE_BOLD = "\033[1;35m";
     * // PURPLE private static final String CYAN_BOLD = "\033[1;36m"; // CYAN
     * private static final String WHITE_BOLD = "\033[1;37m"; // WHITE
     * 
     * // Underline private static final String BLACK_UNDERLINED = "\033[4;30m"; //
     * BLACK private static final String RED_UNDERLINED = "\033[4;31m"; // RED
     * private static final String GREEN_UNDERLINED = "\033[4;32m"; // GREEN private
     * static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW private
     * static final String BLUE_UNDERLINED = "\033[4;34m"; // BLUE private static
     * final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE private static final
     * String CYAN_UNDERLINED = "\033[4;36m"; // CYAN private static final String
     * WHITE_UNDERLINED = "\033[4;37m"; // WHITE
     * 
     * // Background private static final String BLACK_BACKGROUND = "\033[40m"; //
     * BLACK private static final String RED_BACKGROUND = "\033[41m"; // RED private
     * static final String GREEN_BACKGROUND = "\033[42m"; // GREEN private static
     * final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW private static final
     * String BLUE_BACKGROUND = "\033[44m"; // BLUE private static final String
     * PURPLE_BACKGROUND = "\033[45m"; // PURPLE private static final String
     * CYAN_BACKGROUND = "\033[46m"; // CYAN private static final String
     * WHITE_BACKGROUND = "\033[47m"; // WHITE
     * 
     * // High Intensity private static final String BLACK_BRIGHT = "\033[0;90m"; //
     * BLACK private static final String RED_BRIGHT = "\033[0;91m"; // RED private
     * static final String GREEN_BRIGHT = "\033[0;92m"; // GREEN private static
     * final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW private static final
     * String BLUE_BRIGHT = "\033[0;94m"; // BLUE private static final String
     * PURPLE_BRIGHT = "\033[0;95m"; // PURPLE private static final String
     * CYAN_BRIGHT = "\033[0;96m"; // CYAN private static final String WHITE_BRIGHT
     * = "\033[0;97m"; // WHITE
     * 
     * // Bold High Intensity private static final String BLACK_BOLD_BRIGHT =
     * "\033[1;90m"; // BLACK private static final String RED_BOLD_BRIGHT =
     * "\033[1;91m"; // RED private static final String GREEN_BOLD_BRIGHT =
     * "\033[1;92m"; // GREEN private static final String YELLOW_BOLD_BRIGHT =
     * "\033[1;93m";// YELLOW private static final String BLUE_BOLD_BRIGHT =
     * "\033[1;94m"; // BLUE private static final String PURPLE_BOLD_BRIGHT =
     * "\033[1;95m";// PURPLE private static final String CYAN_BOLD_BRIGHT =
     * "\033[1;96m"; // CYAN private static final String WHITE_BOLD_BRIGHT =
     * "\033[1;97m"; // WHITE
     * 
     * // High Intensity backgrounds private static final String
     * BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK private static final String
     * RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED private static final String
     * GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN private static final String
     * YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW private static final
     * String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE private static final
     * String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE private static
     * final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m"; // CYAN private static
     * final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m"; // WHITE
     */
}
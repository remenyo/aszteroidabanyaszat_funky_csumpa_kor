package src;

/**
 * Log osztály.
 * 
 */
public class Log {

    public static final Log INSTANCE = new Log();

    // a színek innen vannak: https://stackoverflow.com/a/45444716
    public static final String RESET = Jatek.COLOR_IN_TERMINAL ? "\033[0m" : "";

    public static final String RED = Jatek.COLOR_IN_TERMINAL ? "\033[0;31m" : ""; // RED
    public static final String YELLOW = Jatek.COLOR_IN_TERMINAL ? "\033[0;33m" : ""; // YELLOW
    public static final String BLUE = Jatek.COLOR_IN_TERMINAL ? "\033[0;34m" : ""; // BLUE
    public static final String PURPLE = Jatek.COLOR_IN_TERMINAL ? "\033[0;35m" : ""; // PURPLE
    public static final String BLACK = Jatek.COLOR_IN_TERMINAL ? "\033[0;30m" : ""; // BLACK
    public static final String GREEN = Jatek.COLOR_IN_TERMINAL ? "\033[0;32m" : ""; // GREEN
    public static final String CYAN = Jatek.COLOR_IN_TERMINAL ? "\033[0;36m" : ""; // CYAN
    public static final String WHITE_BG = Jatek.COLOR_IN_TERMINAL ? "\033[47m" : ""; // WHITE

    private Log() {

    }


    /**
     * Visszaadja a stack {@code top-offset}-edik elemét.
     * 
     * @param offset Hívási sorban visszatekintés mélysége
     * @return StackTraceElement A stack {@code top-offset}-edik eleme.
     */
    private static StackTraceElement getCaller(int offset) {
        // https://stackoverflow.com/a/4065546 az alap innen jött
        return Thread.currentThread().getStackTrace()[4 + offset]; // ?? magic
    }


    /**
     * Részletes log stringet építõ függvény. Az átadott hívó objektum részletesen lesz kiírva.
     * 
     * @param level A sor elején olvasható címke pl. INFO vagy WARN
     * @param e A logot küldõ objektum
     * @return teljes log string
     */
    private static String verboseLogBuilder(String level, StackTraceElement e) {
        return "[" + level + RESET + "] " + e.toString();
    }


    /**
     * Egyszerû log stringet építõ függvény. Az átadott hívó objektum pár információja lesz csak
     * leírva.
     * 
     * @param level A sor elején olvasható címke pl. INFO vagy WARN
     * @param e A logot küldõ objektum
     * @return teljes log string
     */
    private static String simpleLogBuilder(String level, StackTraceElement e) {
        return "[" + level + RESET + "] " /* + e.getClassName() + "." + e.getMethodName() */;
    }


    /**
     * Log fõ függvény
     * 
     * @param level A sor elején olvasható címke pl. INFO vagy WARN
     * @param message A loghoz tartozó üzenet (opcionális)
     * @param verbose A log részletessége (true = hívó részletes leírása)
     * @param magic_stack_pointer A hívó stackban található helye
     */
    private static void log(String level, String message, boolean verbose,
            int magic_stack_pointer) {
        if (verbose)
            System.out.println(verboseLogBuilder(level, getCaller(magic_stack_pointer))
                    + (message.equals("") ? "" : ": ") + message);
        else
            System.out.println(simpleLogBuilder(level, getCaller(magic_stack_pointer))
                    + (message.equals("") ? "" : ": ") + message);
    }


    /**
     * Error üzenet logolása.
     * 
     * @param message A loghoz tartozó üzenet (opcionális)
     */
    static void error(String message) {
        error(message, 0, 1);
    }


    /**
     * Error üzenet logolása.
     * 
     * @param message A loghoz tartozó üzenet (opcionális)
     * @param options elsõ argumentum: 1 = részletes hívó leírás, 0 = egyszerû hívó leírás ; második
     *        argumentum: stack pointer offset
     */
    static void error(String message, Integer... options) {
        if (Jatek.LOG_LEVEL > 0)
            log(RED + "ERROR", message, options[0] == 1, options[1]);
    }


    /**
     * Warn üzenet logolása.
     * 
     * @param message A loghoz tartozó üzenet (opcionális)
     */
    static void warn(String message) {
        warn(message, 0, 1);
    }


    /**
     * Warn üzenet logolása.
     * 
     * @param message A loghoz tartozó üzenet (opcionális)
     * @param options elsõ argumentum: 1 = részletes hívó leírás, 0 = egyszerû hívó leírás ; második
     *        argumentum: stack pointer offset
     */
    static void warn(String message, Integer... options) {
        if (Jatek.LOG_LEVEL > 1)
            log(YELLOW + "WARN", message, options[0] == 1, options[1]);
    }


    /**
     * Info üzenet logolása.
     * 
     * @param message A loghoz tartozó üzenet (opcionális)
     */
    static void info(String message) {
        info(message, 0, 1);
    }


    /**
     * Info üzenet logolása.
     * 
     * @param message A loghoz tartozó üzenet (opcionális)
     * @param options elsõ argumentum: 1 = részletes hívó leírás, 0 = egyszerû hívó leírás ; második
     *        argumentum: stack pointer offset
     */
    static void info(String message, Integer... options) {
        if (Jatek.LOG_LEVEL > 2)
            log(BLUE + "INFO", message, options[0] == 1, options[1]);
    }


    /**
     * Debug üzenet logolása.
     * 
     * @param message A loghoz tartozó üzenet (opcionális)
     */
    static void debug(String message) {
        debug(message, 0, 1);
    }


    /**
     * Debug üzenet logolása.
     * 
     * @param message A loghoz tartozó üzenet (opcionális)
     * @param options elsõ argumentum: 1 = részletes hívó leírás, 0 = egyszerû hívó leírás ; második
     *        argumentum: stack pointer offset
     */
    static void debug(String message, Integer... options) {
        if (Jatek.LOG_LEVEL > 3)
            log(PURPLE + "DEBUG", message, options[0] == 1, options[1]);
    }

    /**
     * Függvényhívás logoló függvény.
     * 
     * A kiírás be/ki kapcsolható az Jatek.LOG_FUNCTION_CALLS beállítással.
     * 
     * @see Jatek
     */
    static void call() {
        if (Jatek.LOG_FUNCTION_CALLS)
            log(GREEN + "CALL", "", true, 0);
    }


    /**
     * A játékosnak lehet üzenni ezzel a függvénnyel
     * 
     * @param message Az üzenet
     */
    static void jatek(String message) {
        if (Jatek.LOG_GAME_INFO)
            log(GREEN + "JATEK", message, false, 0);
    }

    /**
     * Konstruktorhívást logoló függvény.
     * 
     * A kiírás ki/be - kapcsolható az Jatek.LOG_CONSTRUCTORS beállítással.
     * 
     * @see Jatek
     */
    static void ctor() {
        if (Jatek.LOG_CONSTRUCTORS)
            log(CYAN + "CTOR", "", true, 0);
    }
}

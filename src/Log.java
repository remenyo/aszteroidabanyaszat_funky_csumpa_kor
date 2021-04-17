package src;

/**
 * Log oszt�ly.
 * 
 */
public class Log {

    public static final Log INSTANCE = new Log();

    // a sz��nek innen vannak: https://stackoverflow.com/a/45444716
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
     * Visszaadja a stack {@code top-offset}-edik elem�t.
     * 
     * @param offset H��v�si sorban visszatekint�s m�lys�ge
     * @return StackTraceElement A stack {@code top-offset}-edik eleme.
     */
    private static StackTraceElement getCaller(int offset) {
        // https://stackoverflow.com/a/4065546 az alap innen j�tt
        return Thread.currentThread().getStackTrace()[4 + offset]; // ?? magic
    }


    /**
     * R�szletes log stringet �p��t� f�ggv�ny. Az �tadott h��v� objektum r�szletesen lesz ki��rva.
     * 
     * @param level A sor elej�n olvashat� c��mke pl. INFO vagy WARN
     * @param e A logot k�ld� objektum
     * @return teljes log string
     */
    private static String verboseLogBuilder(String level, StackTraceElement e) {
        return "[" + level + RESET + "] " + e.toString();
    }


    /**
     * Egyszer� log stringet �p��t� f�ggv�ny. Az �tadott h��v� objektum p�r inform�ci�ja lesz csak
     * le��rva.
     * 
     * @param level A sor elej�n olvashat� c��mke pl. INFO vagy WARN
     * @param e A logot k�ld� objektum
     * @return teljes log string
     */
    private static String simpleLogBuilder(String level, StackTraceElement e) {
        return "[" + level + RESET + "] " /* + e.getClassName() + "." + e.getMethodName() */;
    }


    /**
     * Log f� f�ggv�ny
     * 
     * @param level A sor elej�n olvashat� c��mke pl. INFO vagy WARN
     * @param message A loghoz tartoz� �zenet (opcion�lis)
     * @param verbose A log r�szletess�ge (true = h��v� r�szletes le��r�sa)
     * @param magic_stack_pointer A h��v� stackban tal�lhat� helye
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
     * Error �zenet logol�sa.
     * 
     * @param message A loghoz tartoz� �zenet (opcion�lis)
     */
    static void error(String message) {
        error(message, 1, 1);
    }


    /**
     * Error �zenet logol�sa.
     * 
     * @param message A loghoz tartoz� �zenet (opcion�lis)
     * @param options els� argumentum: 1 = r�szletes h��v� le��r�s, 0 = egyszer� h��v� le��r�s ;
     *        m�sodik argumentum: stack pointer offset
     */
    static void error(String message, Integer... options) {
        if (Jatek.LOG_LEVEL > 0)
            log(RED + "ERROR", message, options[0] == 1, options[1]);
    }


    /**
     * Warn �zenet logol�sa.
     * 
     * @param message A loghoz tartoz� �zenet (opcion�lis)
     */
    static void warn(String message) {
        warn(message, 0, 1);
    }


    /**
     * Warn �zenet logol�sa.
     * 
     * @param message A loghoz tartoz� �zenet (opcion�lis)
     * @param options els� argumentum: 1 = r�szletes h��v� le��r�s, 0 = egyszer� h��v� le��r�s ;
     *        m�sodik argumentum: stack pointer offset
     */
    static void warn(String message, Integer... options) {
        if (Jatek.LOG_LEVEL > 1)
            log(YELLOW + "WARN", message, options[0] == 1, options[1]);
    }


    /**
     * Info �zenet logol�sa.
     * 
     * @param message A loghoz tartoz� �zenet (opcion�lis)
     */
    static void info(String message) {
        info(message, 0, 1);
    }


    /**
     * Info �zenet logol�sa.
     * 
     * @param message A loghoz tartoz� �zenet (opcion�lis)
     * @param options els� argumentum: 1 = r�szletes h��v� le��r�s, 0 = egyszer� h��v� le��r�s ;
     *        m�sodik argumentum: stack pointer offset
     */
    static void info(String message, Integer... options) {
        if (Jatek.LOG_LEVEL > 2)
            log(BLUE + "INFO", message, options[0] == 1, options[1]);
    }


    /**
     * Debug �zenet logol�sa.
     * 
     * @param message A loghoz tartoz� �zenet (opcion�lis)
     */
    static void debug(String message) {
        debug(message, 0, 1);
    }


    /**
     * Debug �zenet logol�sa.
     * 
     * @param message A loghoz tartoz� �zenet (opcion�lis)
     * @param options els� argumentum: 1 = r�szletes h��v� le��r�s, 0 = egyszer� h��v� le��r�s ;
     *        m�sodik argumentum: stack pointer offset
     */
    static void debug(String message, Integer... options) {
        if (Jatek.LOG_LEVEL > 3)
            log(PURPLE + "DEBUG", message, options[0] == 1, options[1]);
    }

    /**
     * F�ggv�nyh��v�s logol� f�ggv�ny.
     * 
     * A ki��r�s be/ki kapcsolhat� az Jatek.LOG_FUNCTION_CALLS be�ll��t�ssal.
     * 
     * @see Jatek
     */
    static void call() {
        if (Jatek.LOG_FUNCTION_CALLS)
            log(GREEN + "CALL", "", true, 0);
    }


    /**
     * A j�t�kosnak lehet �zenni ezzel a f�ggv�nnyel
     * 
     * @param message Az �zenet
     */
    static void userInfo(String message) {
        if (Jatek.LOG_GAME_INFO)
            log(GREEN + "JATEK", message, false, 0);
    }

    /**
     * Konstruktorh��v�st logol� f�ggv�ny.
     * 
     * A ki��r�s ki/be - kapcsolhat� az Jatek.LOG_CONSTRUCTORS be�ll��t�ssal.
     * 
     * @see Jatek
     */
    static void ctor() {
        if (Jatek.LOG_CONSTRUCTORS)
            log(CYAN + "CTOR", "", true, 0);
    }
}

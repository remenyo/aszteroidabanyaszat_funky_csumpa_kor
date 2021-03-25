package src;

public class App {

    public static boolean COLOR_IN_TERMINAL = true;
    public static final boolean LOG_FUNCTION_CALLS = true;
    public static final boolean LOG_CONSTRUCTORS = true;
    // -1 = semmi | 0 = ctor �s call, ha enged�lyezve vannak, 1 = csak error | 2 = 1+warn | 3 =
    // 2+info | 4 = 3+debug
    public static final int LOG_LEVEL = 4;

    public static void main(String[] args) throws Exception {
        Log.info("Program elindult");

        do {
            Szkeleton szkeleton = new Szkeleton();
            szkeleton.Menu();
        } while (Cin.getBool("Kezdj�k �jra?"));

        Log.info("A j�t�k le�llt.");

        System.out.println("Nyomja meg az entert a kil�p�shez...");
        System.in.read();
    }
}

package src;

public class App {

    public static void main(String[] args) throws Exception {
        Szkeleton.reset();
        Log.info("Program elindult");

        do {
            Szkeleton.Fomenu();
            Szkeleton.reset();
        } while (Cin.getBool("Kezdj�k �jra?"));

        Log.info("A program le�llt.");

        // System.out.println("Nyomja meg az entert a kil�p�shez...");
        // System.in.read();
    }
}

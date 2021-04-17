package src;

public class App {

    public static void main(String[] args) throws Exception {
        Jatek.beallitas_mentes();
        Szkeleton.reset();
        Log.info("Program elindult");

        do {
            Szkeleton.Fomenu();
            Szkeleton.reset();
        } while (Cin.getBool("Kezdjük újra?"));

        Log.info("A program leállt.");

        // System.out.println("Nyomja meg az entert a kilépéshez...");
        // System.in.read();
    }
}

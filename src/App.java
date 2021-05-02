package src;

public class App {

    public static void main(String[] args) throws Exception {
        Jatek.init();
        Jatek.beallitas_mentes();
        Szkeleton.reset();

        if (args.length == 2) {
            if (args[0].equals("tesztBetoltes")) {
                System.out.println(args[1]);
                Szkeleton.teszt_betoltes(args[1]);
                System.exit(Szkeleton.inkonzisztens_allapot ? 1 : 0);
            }
        }

        Log.info("Program elindult");

        do {
            // Szkeleton.Fomenu();
            Jatek.jatekInditas(true);
            Szkeleton.reset();
        } while (Cin.getBool("Kezdjük újra?"));

        Log.info("A program leállt.");

        // System.out.println("Nyomja meg az entert a KILÉPÉSHEZ...");
        // System.in.read();
    }
}

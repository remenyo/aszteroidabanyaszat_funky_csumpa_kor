package src;

import java.util.Scanner;

public class Cin {

    public static final Cin INSTANCE = new Cin();
    private static Scanner scanner;

    private Cin() {
        scanner = new Scanner(System.in);
    }

    private static void kerdez(String kerdes) {
        System.out.print("\n" + kerdes + " ");
    }

    /**
     * Egy {@code int}-et olvas be a bemenetről. Ha a bemenet nem értelmezhető, a függvény -1-t ad
     * viszza.
     * 
     * @return a beolvasott érték
     */
    public static Integer getInt() {
        String input = "";
        try {
            input = getString();
            return Integer.parseInt(input);
        } catch (Exception e) {
            Log.error("Nem értelmezett bemenet: \"" + input + "\".");
            return -1;

            /*
             * if (s.equals("y") || s.equals("Y")) { Log.info("Válasz átalakítva " + s +
             * "-ról 1-re. ;)"); return 1; } else if (s.equals("n") || s.equals("N")) {
             * Log.info("Válasz átalakítva " + s + "-ről 0-ra. ;)"); return 0; } else {
             * Log.error("Nem értelmezett bemenet: \"" + scanner.next() + "\"."); return 0; }
             */

        }
    }

    /**
     * Egy {@code String}-et olvas be a bemenetről. (egy sort.)
     * 
     * @return a beolvasott érték
     */
    public static String getString() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            Log.error(e.toString());
            return "";
        }
    }

    /**
     * Feltesz egy kérdést a felhasználónak, majd egy {@code int}-et olvas be a bemenetről. Ha a
     * bemenet nem értelmezhető, a függvény -1-t ad vissza.
     * 
     * @param kerdes A felhasználónak kiírandó kérdés.
     * @return A beírt szám, vagy -1 ha nem értlemezhető a bemenet.
     */
    public static Integer getInt(String kerdes) {
        kerdez(kerdes);
        return getInt();
    }

    /**
     * Feltesz egy kérdést a felhasználónak, majd egy {@code String}-et olvas be a bemenetről.
     * 
     * @param kerdes A felhasználónak kiírandó kérdés.
     * @return A beírt szöveg.
     */
    public static String getString(String kerdes) {
        kerdez(kerdes);
        return getString();
    }

    /**
     * Feltesz egy kérdést a felhasználónak, majd egy {@code int}-et olvas be a bemenetről. Ha a
     * bemenet {@code 1}, a visszatérési érték {@code true} különben {@code false}.
     * 
     * @param kerdes A felhasználónak kiírandó kérdés.
     * @return {@code true} vagy {@code false}
     */
    public static Boolean getBool(String kerdes) {
        kerdez(kerdes);
        System.out.println("[1] = igen, [0] = nem");
        return getInt() == 1;
    }

    /**
     * Menü szerepet betöltő függvény.
     * 
     * @param cim Menü neve
     * @param lehetosegek A választható elemek
     * @return Egy int ami {@code 1}-től lehetőségek számáig terjed. {@code -1} ha nem értelmezett a
     *         választás.
     */
    public static Integer kerdez_tobbvalasz(String cim, String... lehetosegek) {
        String border = "";
        for (int i = 0; i < cim.length() + 2; i++) {
            border += "-";
        }
        String kerdes = border + "\n|" + cim + "|\n" + border + "\n";
        for (int i = 0; i < lehetosegek.length; i++) {
            kerdes += i + 1 + " - " + lehetosegek[i] + "\n";
        }
        kerdes += "Válasz [1-" + lehetosegek.length + "]: ";
        kerdez(kerdes);
        int valasz = getInt();
        if (valasz > lehetosegek.length || valasz < 1) {
            Log.warn("Helytelen válasz!", 0, 1);
            valasz = -1;
        }
        return valasz;
    }
}

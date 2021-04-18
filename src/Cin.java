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
        }
    }

    /**
     * Egy {@code String}-et olvas be a bemenetről. (egy sort.)
     * 
     * @return a beolvasott érték
     */
    public static String getString() {
        try {
            return scanner.nextLine(); // new String(scanner.nextLine().getBytes("ISO-8859-1"),
                                       // "UTF-8");
        } catch (Exception e) {
            Log.error(e.toString());
            return "";
        }
    }

    /**
     * Feltesz egy kérdést a felhasználónak, majd egy {@code int}-et olvas be a bemenetről. Ha a
     * bemenet nem értelmezhető, a függvény -1-t ad vissza.
     * 
     * @param kerdes A felhasználónak kiiÍrandó kérdés.
     * @return A beírrt szám, vagy -1 ha nem értlemezhető a bemenet.
     */
    public static Integer getInt(String kerdes) {
        kerdez(kerdes);
        return getInt();
    }

    /**
     * Feltesz egy k�rd�st a felhaszn�l�nak, majd egy {@code String}-et olvas be a bemenetr�l.
     * 
     * @param kerdes A felhaszn�l�nak ki��rand� k�rd�s.
     * @return A be��rt sz�veg.
     */
    public static String getString(String kerdes) {
        kerdez(kerdes);
        return getString();
    }

    /**
     * Feltesz egy k�rd�st a felhaszn�l�nak, majd egy {@code int}-et olvas be a bemenetr�l. Ha a
     * bemenet {@code 1}, a visszat�r�si �rt�k {@code true} k�l�nben {@code false}.
     * 
     * @param kerdes A felhaszn�l�nak ki��rand� k�rd�s.
     * @return {@code true} vagy {@code false}
     */
    public static Boolean getBool(String kerdes) {
        kerdez(kerdes);
        System.out.println("[1] = igen, [0] = nem");
        return getInt() == 1;
    }

    /**
     * Men� szerepet bet�lt� f�ggv�ny.
     * 
     * @param cim Men� neve
     * @param lehetosegek A v�laszthat� elemek
     * @return Egy int ami {@code 1}-t�l lehet�s�gek sz�m�ig terjed. {@code -1} ha nem �rtelmezett a
     *         v�laszt�s.
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

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

    public static int getInt() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            String s = scanner.next();
            if (s.equals("y") || s.equals("Y")) {
                Log.info("Válasz átalakítva " + s + "-ról 1-re. ;)");
                return 1;
            } else if (s.equals("n") || s.equals("N")) {
                Log.info("Válasz átalakítva " + s + "-rõl 0-ra. ;)");
                return 0;
            } else {
                Log.error("Nem értelmezett bemenet: \"" + scanner.next() + "\".");
                return 0;
            }
        }
    }

    public static int getInt(String kerdes) {
        kerdez(kerdes);
        return getInt();
    }

    public static boolean getBool() {
        return getInt() == 1;
    }

    public static boolean getBool(String kerdes) {
        kerdez(kerdes);
        System.out.println("[1] = igen, [0] = nem");
        return getBool();
    }

    public static int kerdez_tobbvalasz(String cim, String... lehetosegek) {
        String kerdes = "-".repeat(cim.length() + 2) + "\n|" + cim + "|\n" + "-".repeat(cim.length() + 2) + "\n";
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

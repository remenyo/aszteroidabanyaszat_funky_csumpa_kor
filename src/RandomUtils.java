package src;

import java.util.Random;

public class RandomUtils {
    public static final RandomUtils INSTANCE = new RandomUtils();
    private static Random r;

    private RandomUtils() {
        r = new Random();
    }

    /**
     * Visszaad véletlenszerûen egy egész számot a megadott két határ között, a határokat is
     * beleértve. Az alsó határ nem kell hogy nagyobb legyen a felsõnél. Mindkettõ érték lehet
     * negatív.
     *
     * @param alsoHatar Az alsó határ
     * @param felsoHatar Az felsõ határ
     */
    public static int randomIntHatarokKozott(int alsoHatar, int felsoHatar) {
        if (alsoHatar > felsoHatar) {
            Log.debug(
                    "randomIntHatarokKozott: Alsó határ nagyobb mint a felsõ, megcseréltem. ("
                            + alsoHatar + ">" + felsoHatar + ") A hívó neve található a debugban.",
                    1, 1);
            int a = alsoHatar;
            alsoHatar = felsoHatar;
            felsoHatar = a;
        }
        int valaszthatoSzamok = felsoHatar - alsoHatar + 1; // darab
        return r.nextInt(valaszthatoSzamok) + alsoHatar;
    }

    /**
     * Visszaad egy booleant, ami {@code valoszinuseg} valószínûséggel lesz igaz értékû. (A
     * pontosság 5.)
     *
     * @param valoszinuseg (0.0 - 1.0) {@code true} valószínûsége
     */
    public static boolean randomBooleanValoszinuseggel(double valoszinuseg) {
        return randomBooleanValoszinuseggel(valoszinuseg, 5);
    }

    /**
     * Visszaad egy booleant, ami {@code valoszinuseg} valószínûséggel lesz igaz értékû.
     *
     * @param valoszinuseg (0.0 - 1.0) a {@code true} valószínûsége
     * @param pontossag (1-7) minnél nagyobb, annál közelebb lesz a valódi valószínûség a kívánt
     *        értékhez
     */
    private static boolean randomBooleanValoszinuseggel(double valoszinuseg, int pontossag) {
        pontossag = Math.min(7, Math.max(1, pontossag));
        if (valoszinuseg >= 1) {
            Log.debug("1-nél nagyobb vagy egyenlõ valószínûség -> igen", 1, 1);
            return true;
        }
        if (valoszinuseg <= 0) {
            Log.debug("0-nál kisebb vagy egyenlõ valószínûség -> nem", 1, 1);
            return false;
        }

        double szorzo = Math.pow(10.0, pontossag);
        return (r.nextInt((int) szorzo) <= ((int) (valoszinuseg * szorzo)));
    }

    private void test() {
        // randomIntHatarokKozott.test
        for (int i = 0; i < 500; i++) {
            System.out.println(randomIntHatarokKozott(-22, -3));
        }

        // randomBooleanValoszinuseggel.test
        for (float i = 0; i < 1.1; i += 0.1) { // 0.0 -> 1.0
            float t = 0;
            float f = 0;
            for (int j = 0; j < 1000; j++) {
                if (randomBooleanValoszinuseggel(i, 7))
                    t++;
                else
                    f++;
            }
            System.out.println("True/False expected " + i + ", actual " + t / 1000 + "\t(" + +t
                    + "/" + f + ")");
            t = 0;
            f = 0;
        }
    }
}

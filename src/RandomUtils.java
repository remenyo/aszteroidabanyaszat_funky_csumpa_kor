package src;

import java.util.Random;

public class RandomUtils {
    private static final RandomUtils INSTANCE = new RandomUtils();
    private static Random r;

    public static RandomUtils getInstance() {
        return INSTANCE;
    }

    private RandomUtils() {
        r = new Random();
    }

    /**
     * Visszaad véletlenszerűen egy egész számot a megadott két határ között, a határokat is
     * beleértve. Az alsó határ nem kell hogy nagyobb legyen a felsőnél. Mindkettő érték lehet
     * negatív.
     *
     * @param alsoHatar Az alsó határ
     * @param felsoHatar Az felső határ
     */
    public static Integer randomIntHatarokKozott(int alsoHatar, int felsoHatar) {
        if (alsoHatar > felsoHatar) {
            Log.debug(
                    "randomIntHatarokKozott: Als� hat�r nagyobb mint a fels�, megcser�ltem. ("
                            + alsoHatar + ">" + felsoHatar + ") A h�v� neve tal�lhat� a debugban.",
                    1, 1);
            int a = alsoHatar;
            alsoHatar = felsoHatar;
            felsoHatar = a;
        }
        int valaszthatoSzamok = felsoHatar - alsoHatar + 1; // darab
        return r.nextInt(valaszthatoSzamok) + alsoHatar;
    }

    /**
     * Visszaad egy booleant, ami {@code valoszinuseg} valószínűséggel lesz igaz értékű. (A
     * pontosság 5.)
     *
     * @param valoszinuseg (0.0 - 1.0) {@code true} valószínűsége
     */
    public static Boolean randomBooleanValoszinuseggel(double valoszinuseg) {
        return randomBooleanValoszinuseggel(valoszinuseg, 5);
    }

    /**
     * Visszaad egy booleant, ami {@code valoszinuseg} valószínűséggel lesz igaz értékű.
     *
     * @param valoszinuseg (0.0 - 1.0) a {@code true} valószínűsége
     * @param pontossag (1-7) minnél nagyobb, annál közelebb lesz a valódi valószínűség a kívánt
     *        értékhez
     */
    private static Boolean randomBooleanValoszinuseggel(double valoszinuseg, int pontossag) {
        pontossag = Math.min(7, Math.max(1, pontossag));
        if (valoszinuseg >= 1) {
            Log.debug("1-n�l nagyobb vagy egyenl� val�sz�n�s�g -> igen", 1, 1);
            return true;
        }
        if (valoszinuseg <= 0) {
            Log.debug("0-n�l kisebb vagy egyenl� val�sz�n�s�g -> nem", 1, 1);
            return false;
        }

        double szorzo = Math.pow(10.0, pontossag);
        return (r.nextInt((int) szorzo) <= ((int) (valoszinuseg * szorzo)));
    }

}

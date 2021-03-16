package src;

import java.util.Random;

public class RandomUtils {
    private Random r;

    RandomUtils() {
        r = new Random();
    }

    /**
     * Visszaad véletlenszerűen egy egész számot a megadott két határérték között, a
     * határokat is beleértve. Az alsó határ nem kell hogy kisebb legyen a felsőnél.
     * Mindkettő érték lehet negatív.
     *
     * @param alsoHatar  Az alsó határ
     * @param felsoHatar Az felső határ
     */
    public int randomIntHatarokKozott(int alsoHatar, int felsoHatar) {
        if (alsoHatar > felsoHatar) {
            // TODO log warning
            int a = alsoHatar;
            alsoHatar = felsoHatar;
            felsoHatar = a;
        }
        int valaszthatoSzamok = felsoHatar - alsoHatar + 1; // darab
        return r.nextInt(valaszthatoSzamok) + alsoHatar;
    }

    /**
     * Visszaad egy booleant, ami {@code valoszinuseg} valószínűséggel lesz igaz
     * értékű. (A pontosság 5.)
     *
     * @param valoszinuseg (0.0 - 1.0) {@code true} valószínűsége
     */
    public boolean randomBooleanValoszinuseggel(float valoszinuseg) {
        return randomBooleanValoszinuseggel(valoszinuseg, 5);
    }

    /**
     * Visszaad egy booleant, ami {@code valoszinuseg} valószínűséggel lesz igaz
     * értékű.
     *
     * @param valoszinuseg (0.0 - 1.0) a {@code true} valószínűsége
     * @param pontossag    (1-7) minnél nagyobb, annál közelebb lesz a valódi
     *                     valószínűség a kívánt értékhez
     */
    public boolean randomBooleanValoszinuseggel(float valoszinuseg, int pontossag) {
        pontossag = Math.min(7, Math.max(1, pontossag));
        if (valoszinuseg >= 1)
            return true; // TODO log warning
        if (valoszinuseg <= 0)
            return false; // TODO log warning

        double szorzo = Math.pow(10.0, pontossag);
        return (r.nextInt((int) szorzo) <= ((int) (valoszinuseg * szorzo)));
    }
}

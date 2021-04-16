package src;

import java.util.Random;

public class RandomUtils {
    public static final RandomUtils INSTANCE = new RandomUtils();
    private static Random r;

    private RandomUtils() {
        r = new Random();
    }

    /**
     * Visszaad v�letlenszer�en egy eg�sz sz�mot a megadott k�t hat�r k�z�tt, a hat�rokat is
     * bele�rtve. Az als� hat�r nem kell hogy nagyobb legyen a fels�n�l. Mindkett� �rt�k lehet
     * negat�v.
     *
     * @param alsoHatar Az als� hat�r
     * @param felsoHatar Az fels� hat�r
     */
    public static int randomIntHatarokKozott(int alsoHatar, int felsoHatar) {
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
     * Visszaad egy booleant, ami {@code valoszinuseg} val�sz�n�s�ggel lesz igaz �rt�k�. (A
     * pontoss�g 5.)
     *
     * @param valoszinuseg (0.0 - 1.0) {@code true} val�sz�n�s�ge
     */
    public static boolean randomBooleanValoszinuseggel(double valoszinuseg) {
        return randomBooleanValoszinuseggel(valoszinuseg, 5);
    }

    /**
     * Visszaad egy booleant, ami {@code valoszinuseg} val�sz�n�s�ggel lesz igaz �rt�k�.
     *
     * @param valoszinuseg (0.0 - 1.0) a {@code true} val�sz�n�s�ge
     * @param pontossag (1-7) minn�l nagyobb, ann�l k�zelebb lesz a val�di val�sz�n�s�g a k�v�nt
     *        �rt�khez
     */
    private static boolean randomBooleanValoszinuseggel(double valoszinuseg, int pontossag) {
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

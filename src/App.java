package src;

public class App {

    public static void main(String[] args) throws Exception {

        CsumpaLogger.init();

        RandomUtils r = new RandomUtils();
        System.out.println("Hello, World!");

        // randomIntHatarokKozott.test
        for (int i = 0; i < 500; i++) {
            System.out.println(r.randomIntHatarokKozott(-22, -3));
        }

        // randomBooleanValoszinuseggel.test
        for (float i = 0; i < 1.1; i += 0.1) { // 0.0 -> 1.0
            float t = 0;
            float f = 0;
            for (int j = 0; j < 1000; j++) {
                if (r.randomBooleanValoszinuseggel(i, 7))
                    t++;
                else
                    f++;
            }
            System.out.println("True/False expected " + i + ", actual " + t / 1000 + "\t(" + +t + "/" + f + ")");
            t = 0;
            f = 0;
        }
    }
}

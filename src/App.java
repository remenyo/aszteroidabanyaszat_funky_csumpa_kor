package src;

public class App {

    public static void main(String[] args) throws Exception {
        Log.info("Program elindult");

        do {
            Szkeleton szkeleton = new Szkeleton();
            szkeleton.Menu();
        } while (Cin.getBool("Kezdj�k �jra?"));

        Log.info("A j�t�k le�llt.");

        System.out.println("Nyomjon meg egy gombot a kil�p�shez...");
        System.in.readNBytes(1);
    }
}

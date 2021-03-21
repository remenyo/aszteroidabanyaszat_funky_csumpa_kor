package src;

public class App {

    public static void main(String[] args) throws Exception {
        Log.info("Program elindult");

        do {
            Szkeleton szkeleton = new Szkeleton();
            szkeleton.Menu();
        } while (Cin.getBool("Kezdjük újra?"));

        Log.info("A játék leállt.");

        System.out.println("Nyomjon meg egy gombot a kilépéshez...");
        System.in.readNBytes(1);
    }
}

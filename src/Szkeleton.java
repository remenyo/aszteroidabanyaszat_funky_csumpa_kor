package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;

public class Szkeleton {
	// TODO ez már nem csak szkeleton, most már ez a "játék"

	private static final Szkeleton INSTANCE = new Szkeleton();

	public static Szkeleton getInstance() {
		return INSTANCE;
	}

	private static Map<String, Object> objektumok;

	private static ArrayList<String> filebaIrando = new ArrayList<String>();

	private Szkeleton() {
		objektumok = new TreeMap<>();
	}

	public static void Fomenu() {
		while (true) {
			int valasz = Cin.kerdez_tobbvalasz("F�men�", "J�t�k ind�t�s", "Parancssor",
					"Teszt bet�lt�s", "J�t�k alaphelyzetbe �ll�t�sa", "Kil�p�s");
			switch (valasz) {
				case 1:
					Jatek.jatekInditas();
					break;
				case 2:
					teszt_parancssor();
					break;
				case 3:
					teszt_betoltes(Cin.getString("A f�jl neve/c�me:"));
					break;
				case 4:
					teszt_reset();
					break;
				case 5:
					if (Cin.getBool("Ez t�rli a j�t�k �llapot�t, biztos vagy benne?"))
						return;
				default:
					break;
			}
		}
	};

	private static List<File> fajlBetoltes(String... argumentumok) {
		List<File> files_to_test = new ArrayList<File>();

		Boolean arg_should_be_dir = false;
		String dir = "";
		if (argumentumok.length == 0) {
			arg_should_be_dir = true;
			dir = System.getProperty("user.dir");
		} else if (argumentumok.length == 1) {
			arg_should_be_dir = argumentumok[0].contains(".");
			dir = argumentumok[0];
		}
		if (arg_should_be_dir) {
			try (Stream<Path> stream = Files.list(Paths.get(dir))) {
				files_to_test = stream.filter(file -> {
					if (Files.isDirectory(file))
						return false;
					String name = file.getFileName().toString();
					return (!name.contains("elvart")) && (!name.contains("eredmeny")
							&& name.substring(name.lastIndexOf(".") + 1).equals("txt"));
				}).map(Path::toFile).collect(Collectors.toList());
			} catch (Exception e) {
				Log.error(e.toString());
			}
		} else {// argumentumok.length >= 2 --> mind file
			for (String fileName : argumentumok) {
				try {
					File f = Paths.get(System.getProperty("user.dir"), fileName).toFile();
					files_to_test.add(f);
				} catch (Exception e) {
					Log.error(e.toString());
				}
			}
		}
		return files_to_test;
	}

	private static void athelyez_regi_eredmenyek() {
		Path cel = null;
		try {
			cel = Files.createDirectories(Paths.get(System.getProperty("user.dir"), "eredmenyek"));
		} catch (Exception e) {
			Log.error(e.toString());
		}

		try (Stream<Path> stream = Files.list(Paths.get(System.getProperty("user.dir")))) {
			for (File file : stream
					.filter(file -> file.getFileName().toString().contains("eredmeny")
							&& !Files.isDirectory(file))
					.map(Path::toFile).collect(Collectors.toList())) {
				Path dest = Paths.get(cel.toAbsolutePath().toString(), file.getName());
				Path src = Paths.get(file.getAbsolutePath());
				while (Files.exists(dest)) {
					dest = Paths.get(cel.toAbsolutePath().toString(), file.getName() + "_ujabb");
				}
				Files.move(src, dest);
			}
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	public static void teszt_betoltes(String... argumentumok) {
		List<File> files = fajlBetoltes(argumentumok);
		athelyez_regi_eredmenyek();
	}

	public static void teszt_parancssor() {
		while (true) {
			System.out.print("> ");
			String parancs = Cin.getString();
			parancs = parancs.replaceAll("\\s+", "");
			if (parancs.equals(""))
				continue;
			if (parancs.toLowerCase().equals("kilepes")) {
				return;
			} else if (parancs.toLowerCase().equals("parancssor")) {
				Log.info("M�r a parancssorban vagy.");
			} else {
				String[] argumentumok = parancs.split(":");
				if (argumentumok.length == 1) {
					parancs(argumentumok[0]);
				} else if (argumentumok.length >= 2) {
					parancs(argumentumok[0],
							Arrays.copyOfRange(argumentumok, 1, argumentumok.length));
				}
			}
		}
	}

	protected static void reset() {
		objektumok.clear();
		objektumok.put("_this", getInstance());
		objektumok.put("jatek", Jatek.getInstance());
		// Jatek.restoreBackup();
		objektumok.put("nap", new Nap());
		// TODO itt bele kell rakni a játék automatikusan létrehozott globális objektumait a tömbbe.

		Log.info("RESET");
	}

	public static void teszt_reset() {
		if (Cin.getBool(
				"A program minden beállítása alapértelmezett értékre áll vissza, és minden létrehozott objektum törlődik. Biztos vagy benne?")) {
			reset();
		}
	}

	private static void parancs(String parancs, String... argumentumok) {
		hiv("_this", "teszt_" + parancs, argumentumok);
	}

	/**
	 * Visszaad egy tipus típusú objektumot, aminek az értéke az ertek. Ha létezik ertek
	 * azonosítóval tipus típusú objektum az objektumok tömbben, akkor azt az objektumot adja
	 * vissza. Ha nem sikeres az átalakítás, a függvény kivételt generál.
	 * 
	 * @param tipus A visszaadandó objektum típusa
	 * @param ertek Az objektum értéke
	 * @return {@code tipus} típusú, {@code ertek} értékű objektum
	 * @throws Exception Ha nem sikerül az átlakítás, kivételt generál a függvény.
	 */
	private static Object egyParameterTipusForditas(Class<?> tipus, String ertek) throws Exception {
		// van ilyen objektumunk tárolva véletlen?
		if (objektumok.containsKey(ertek.toLowerCase())) {
			if (tipus.isInstance(objektumok.get(ertek))) {

				return objektumok.get(ertek);
			}
		}
		// fallback
		if (tipus.equals(ertek.getClass()))
			return ertek;
		if (ertek.equals("null"))
			return null;
		else {
			try {
				return tipus.getMethod("valueOf", String.class).invoke(null, ertek);
			} catch (Exception e) {
				Log.error(e.toString());
				Log.debug(tipus.toString() + " " + ertek.toString());
				throw new Exception("Fordítás sikertelen", e);
			}
		}
	}

	/**
	 * Az ertekek tömb érékeit a tipus tömb szerinti típusú objektumokká alakítja, majd visszaadja
	 * őket egy tömbben. Hiba esetén null-al tér vissza.
	 * 
	 * @param tipusok kívánt típusok
	 * @param ertekek kívánt értékek
	 * @return {@code tipus} típusú, {@code ertek} értékű objektum tömb
	 */
	private static Object[] tobbParameterTipusForditas(Class<?>[] tipusok, String[] ertekek) {
		if (tipusok.length == ertekek.length) {
			ArrayList<Object> parameterek = new ArrayList<>();
			for (int i = 0; i < tipusok.length; i++) {
				try {
					parameterek.add(egyParameterTipusForditas(tipusok[i], ertekek[i]));
				} catch (Exception e) {
					Log.error(e.toString());
					return null;
				}
			}
			return parameterek.toArray();
		}
		return null;
	}

	/**
	 * Megkeres és visszaadja a cls osztály vagy annak ősében található adattag_nev nevű adattagot.
	 * 
	 * @param cls A keresendő osztály
	 * @param adattag_nev A keresendő adattag név
	 * @return A megtalált adattag. Ha nem létezik, nullal tér vissza.
	 */
	private static Field adattagKereses(Class<?> cls, String adattag_nev) {
		do {
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				if (field.getName().equals(adattag_nev))
					return field;
			}
			cls = cls.getSuperclass();
		} while (cls != null);
		return null;
	}

	/**
	 * Létrehoz egy tipus típusú objektumot az argumentumok felhasználásával, majd elmenti az
	 * objektumok tömbbe, id azonosítóval. Ha nem talál megfelelő konstruktort, vagy nem tudja
	 * átalakítani az argumentumokat megfelelő típusra vagy az id azonosító foglalt, nem hoz létre
	 * semmit.
	 * 
	 * @param tipus
	 * @param id
	 * @param argumentumok
	 */
	public static void letrehoz(String tipus, String id, String... argumentumok) {
		if (objektumok.containsKey(id)) {
			Log.error("A megadott azonosító már létezik! (" + id + ")");
			return;
		}
		try {
			Class<?> cls = Class.forName("src." + tipus);
			Constructor<?>[] constructors = cls.getDeclaredConstructors();
			// végignézem a konstruktorokat
			for (Constructor<?> constructor : constructors) {
				Object[] tipusos_parameterek =
						tobbParameterTipusForditas(constructor.getParameterTypes(), argumentumok);

				if (tipusos_parameterek != null) { // A LÉNYEG
					try {
						objektumok.put(id.toLowerCase(),
								constructor.newInstance(tipusos_parameterek));
						Log.info(id + " " + cls.getName() + " Sikeresen létrehozva");
						break;
					} catch (Exception e) {
						Log.error(e.toString());
					}
				}
			}
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	/**
	 * Az id azonosítójú objektumon meghívja a fuggveny_nev nevű függvényt az argumentumok
	 * argumentumokkal. A visszatérési érték a hívott függvény eredménye
	 * 
	 * @param id
	 * @param fuggveny_nev
	 * @param argumentumok
	 * @return
	 */
	public static Object hiv(String id, String fuggveny_nev, String... argumentumok) {
		if (!objektumok.containsKey(id)) {
			Log.error("Az azonosító nem létezik! (" + id + ")");
			return null;
		}
		Class<?> cls = objektumok.get(id).getClass();
		Method[] fuggvenyek = cls.getMethods();
		for (Method fuggveny : fuggvenyek) {
			if (fuggveny.getName().equals(fuggveny_nev)) {
				Object[] tipusos_parameterek =
						tobbParameterTipusForditas(fuggveny.getParameterTypes(), argumentumok);
				if (tipusos_parameterek != null) {
					try {
						return fuggveny.invoke(objektumok.get(id), tipusos_parameterek);
					} catch (Exception e) {
						Log.warn(
								"A függvényhívás hívás nem sikerült. A program inkonzisztens állapotba kerülhetett.");
						Log.debug(e.toString());
					}
				}
			}
		}
		Log.warn(fuggveny_nev + " nem található, vagy a megadott paraméterek nem megfelelőek. ("
				+ cls.getName() + " osztályon hívva).");
		return null;
	}

	/**
	 * Az id azonosítójú objektum adattag_neve nevű adattagját uj_ertek értékre állítja be, ha
	 * létezik az adattag, és átalakítható az uj_ertek az adattag típusára.
	 * 
	 * @param id
	 * @param adattag_neve
	 * @param uj_ertek
	 */
	public static void beallit(String id, String adattag_neve, String uj_ertek) {
		Field adattag = adattagKereses(objektumok.get(id).getClass(), adattag_neve);
		int modifier = adattag.getModifiers();
		boolean private_field = Modifier.isPrivate(modifier);
		if (private_field) {
			Log.warn(Modifier.toString(modifier) + " adattag (" + adattag.getName()
					+ ") beállítása.");
			adattag.setAccessible(true);
		}

		if (adattag != null) {
			try {
				Object beallitando_ertek = egyParameterTipusForditas(adattag.getType(), uj_ertek);
				adattag.set(objektumok.get(id), beallitando_ertek);
			} catch (Exception e) {
				Log.error(e.toString());
			}
		}
		if (private_field) {
			adattag.setAccessible(private_field);
		}
	}

	public static String info(String id) {
		try {
			return String.class.cast(hiv(id, "toString"));
		} catch (Exception e) {
			Log.error(e.toString());
		}
		return "";
	}

	public static String getID(Object object) {
		for (Entry<String, Object> e : objektumok.entrySet()) {
			if (e.getValue().equals(object)) {
				return e.getKey();
			}
		}
		return "<ismeretlen " + object.getClass().getName() + ">";
	}

	public static void JatekMenu() {
		switch (Cin.kerdez_tobbvalasz("MENÜ", "Mozgás űrhajóval", "Mozgás teleport kapun keresztül",
				"Bányászat", "Vízjég Fúrás", "Urán fúrás", "Fúrás vas", "Portálkapu építés",
				"Robot építés", "Nyersanyag visszahelyezés", "Portál lehelyezés",
				"Robot Urán fúrás", "Napvihar")) {
			// TODO

			default:
				break;
		}
	}

	public static void BanyaszatMenu() {
		switch (Cin.kerdez_tobbvalasz("BÁNYÁSZAT", "Urán Bányászat", "Vízjég Bányászat",
				"Szén Bányászat", "Vas Bányászat")) {
			// TODO

			default:
				break;
		}
	}

	public static void NyersanyagVisszahelyezesMenu() {
		System.out.println("1. Urán visszahelyezés\r\n" + "2. Vízjég visszahelyezés\r\n"
				+ "3. Szén visszahelyezés\r\n" + "4. Vas visszahelyezés");
		switch (Cin.kerdez_tobbvalasz("BÁNYÁSZAT", "Urán visszahelyezés", "Vízjég visszahelyezés",
				"Szén visszahelyezés", "Vas visszahelyezés")) {
			// TODO

			default:
				break;
		}
	}

	public static void teszt_letrehozPortalAszteroida(String pid, String aid ) {
		letrehoz("Portal", pid);
		beallit(pid, "aszteroida", aid);
	}

	public static void teszt_osszekotAszteroida(String aid1, String aid2) {
		hiv(aid1, "hozzaadSzomszed", aid2);
		hiv(aid2, "hozzaadSzomszed", aid1);
	}

	public static void teszt_osszekotPortal(String pid1, String pid2) {
		hiv(pid1, "beallitPar", pid2);
		hiv(pid2, "beallitPar", pid1);
	}

	public static void teszt_epitRobot(String tid, String rid) {
		Robot r = (Robot) hiv(tid, "epitRobot");
		if (r != null)
			objektumok.put(rid, r);
	}

	public static void teszt_banyaszas(String tid) {
		hiv(tid, "Banyaszat");
	}

	public static void teszt_letrehozNyersanyag(String nyid, String tipus) {
		letrehoz(tipus, nyid);
	}

	public static void teszt_letrehozAszteroida(String aid, String reteg, String napkozel, String nyid) {
		letrehoz("Aszteroida", aid, "nap");
		hiv(aid, "setReteg", reteg);
		hiv(aid, "setNapkozel", napkozel);
		hiv(aid, "setNyersanyag", nyid);
	}

	public static void teszt_letrehozTelepes(String tid, String aid, String[] nyids) {
		letrehoz("Telepes", tid);
		hiv(tid, "beallitAszteroida", aid); // aszteroidan is rajta lesz a
																	// telepes
		//String[] nyersanyagok = Arrays.copyOfRange(argumentumok, 2, argumentumok.length);
		for (int i = 0; i < nyids.length; i++) {
			hiv(tid, "hozzaadNyersanyag", nyids[i]);
		}
	}

	public static void teszt_mozgas(String id, String aid) {
		Aszteroida aminVagyunk = (Aszteroida) hiv(id, "getAszteroida");
		Aszteroida amireMegyunk = ((Aszteroida) objektumok.get(aid));
		Integer menesSzam = aminVagyunk.getSzomszedok().indexOf(amireMegyunk);
		if (menesSzam != -1) {
			hiv(id, "Mozgas", menesSzam.toString()); // TODO ez itt igy jo?
		}

	}

	public static void teszt_info(String id) {
		System.out.println((String) hiv(id, "toString"));
		filebaIrando.add((String) hiv(id, "toString"));
	}

	public static void teszt_mentes(String nev) { // TODO beirni 0. fejezetbe hogy ne
																// irjak oda
		// hogy .txt
		try { // TODO hova mentsen
				// TODO mentes kis m-el
			FileOutputStream kiStream = new FileOutputStream(nev + "_eredmeny.txt");
			OutputStreamWriter kiWriter = new OutputStreamWriter(kiStream, "UTF-8");
			BufferedWriter ir = new BufferedWriter(kiWriter);
			for (String sor : filebaIrando) {
				ir.write(sor);
				ir.write(System.lineSeparator());
			}
			ir.close();
			filebaIrando.clear();
		} catch (Exception e) {
			System.out.println("HIBA");
			e.printStackTrace();
		}
	}

	public static void teszt_infoMinden() {
		for (Map.Entry<String, Object> objektum : objektumok.entrySet()) {
			System.out.println((String) hiv(objektum.getKey(), "toString"));
		}
	}

	public static void teszt_infoAllapot() {
		Integer jelenlegiAllapot = ((Integer) hiv("jatek", "getAllapot"));
		if (jelenlegiAllapot == 0) {
			System.out.println("folyamatban");
			filebaIrando.add("folyamatban");
		} else if (jelenlegiAllapot == 1) {
			System.out.println("nyert");
			filebaIrando.add("nyert");
		} else if (jelenlegiAllapot == -1) {
			System.out.println("vesztett");
			filebaIrando.add("vesztett");
		}

	}

	public static void mindenkiLepett() {
		Jatek jatek = ((Jatek) objektumok.get("jatek"));
		if (jatek.mindenkiLepett()) {
			jatek.resetLepett(); // TODO minden lepes vegere odairni hogy at kell allitani a lepest
									// truera
			// TODO ezt a fuggvenyt is oda kell irni
		}
	}

	// ------------------- Balazs cuccai ---------------
	public static void teszt_letrehozRobot(String rid, String aid) {
		letrehoz("Robot", rid);
		beallit(rid, "aszteroida", aid);
	}

	public static void teszt_letrehozUfo(String uid, String aid) {
		letrehoz("Ufo", uid);
		beallit(uid, "aszteroida", aid);
	}

	public static void teszt_letrehozPortalTelepes(String pid, String tid) {
		if (((Telepes) objektumok.get(pid)).getPortal().size() < 3)
			letrehoz("Portal", pid);
		beallit(pid, "birtokos", tid);
	}

	public static void teszt_visszarakNyersanyag(String tid, String nyid) {
		hiv(tid, "visszarakNyersanyag", nyid);
	}

	public static void teszt_lerakPortal(String tid, String pid) {
		hiv(tid, "lerakPortal", pid);
	}

	public static void teszt_epitPortal(String tid, String pid1, String pid2) {
		ArrayList<Portal> portalok = (ArrayList<Portal>) hiv(tid, "epitPortal");
		if (portalok != null) {
			objektumok.put(pid1, portalok.get(0));
			objektumok.put(pid2, portalok.get(1));
		}
	}

	public static void teszt_furas(String id) {
		hiv(id, "furas", null);
	}

	public static void teszt_napviharOkozasa(String[] aids) { //hehe
		for (int i = 0; i < aids.length; i++) {
			hiv(aids[i], "Napvihar");
		}
	}

	public static void teszt_randomValoszinuseg(String... argumentumok) {
		Jatek.robot_robbanas_elso_szomszed = Boolean.parseBoolean(argumentumok[1]);
	}
}

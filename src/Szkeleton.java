package src;

import java.io.File;
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
	// TODO ez m�r nem csak szkeleton, most m�r ez a "j�t�k"

	private static final Szkeleton INSTANCE = new Szkeleton();

	public static Szkeleton getInstance() {
		return INSTANCE;
	}

	private static Map<String, Object> objektumok;
	private static Map<Field, Object> jatek_alapertelmezes;

	private Szkeleton() {
		objektumok = new TreeMap<>();

		Field[] fields = Jatek.getInstance().getClass().getDeclaredFields();
		for (Field field : fields) {
			int modifier = field.getModifiers();
			boolean private_field = Modifier.isPrivate(modifier);
			if (private_field) {
				field.setAccessible(true);
			}
			try {
				jatek_alapertelmezes.put(field, field.get(Jatek.getInstance()));
			} catch (Exception e) {
				Log.error(e.toString());
			} finally {
				if (private_field) {
					field.setAccessible(false);
				}
			}
		}
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
				case 3:
					teszt_betoltes(Cin.getString("A f�jl neve/c�me:"));
				case 4:
					teszt_reset();
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
				// File test = cel.resolve(null)
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

	private static void resetJatek() {
		for (Entry<Field, Object> e : jatek_alapertelmezes.entrySet()) {
			int modifier = e.getKey().getModifiers();
			boolean private_field = Modifier.isPrivate(modifier);
			if (private_field) {
				e.getKey().setAccessible(true);
			}
			try {
				e.getKey().set(Jatek.getInstance(), e.getValue());
			} catch (Exception exception) {
				Log.error(exception.toString());
			} finally {
				if (private_field) {
					e.getKey().setAccessible(false);
				}
			}
		}
	}

	protected static void reset() {
		objektumok.clear();
		objektumok.put("_this", getInstance());
		objektumok.put("jatek", Jatek.getInstance());
		resetJatek();
		objektumok.put("nap", new Nap());
		// TODO itt bele kell rakni a j�t�k automatikusan l�trehozott glob�lis objektumait a t�mbbe.

		Log.info("RESET");
	}

	public static void teszt_reset() {
		if (Cin.getBool(
				"A program minden be�ll�t�sa alap�rtelmezett �rt�kre �ll vissza, �s minden l�trehozott objektum t�rl�dik. Biztos vagy benne?")) {
			reset();
		}
	}

	private static void parancs(String parancs, String... argumentumok) {
		hiv("_this", "teszt_" + parancs, argumentumok);
	}

	/**
	 * Visszaad egy tipus t�pus� objektumot, aminek az �rt�ke az ertek. Ha l�tezik ertek
	 * azonos�t�val tipus t�pus� objektum az objektumok t�mbben, akkor azt az objektumot adja
	 * vissza. Ha nem sikeres az �talak�t�s, a f�ggv�ny kiv�telt gener�l.
	 * 
	 * @param tipus A visszaadand� objektum t�pusa
	 * @param ertek Az objektum �rt�ke
	 * @return {@code tipus} t�pus�, {@code ertek} �rt�k� objektum
	 * @throws Exception Ha nem siker�l az �tlak�t�s, kiv�telt gener�l a f�ggv�ny.
	 */
	private static Object egyParameterTipusForditas(Class<?> tipus, String ertek) throws Exception {
		// van ilyen objektumunk t�rolva v�letlen?
		if (objektumok.containsKey(ertek.toLowerCase())) {
			if (tipus.isInstance(objektumok.get(ertek))) {

				return objektumok.get(ertek);
			}
		}
		// fallback
		if (ertek.equals("null")) {
			return null;
		} else {
			try {
				return tipus.getMethod("valueOf", String.class).invoke(null, ertek);
			} catch (Exception e) {
				Log.error(e.toString());
				Log.debug(tipus.toString() + " " + ertek.toString());
				throw new Exception("Ford�t�s sikertelen", e);
			}
		}
	}

	/**
	 * Az ertekek t�mb �r�keit a tipus t�mb szerinti t�pus� objektumokk� alak�tja, majd visszaadja
	 * �ket egy t�mbben. Hiba eset�n null-al t�r vissza.
	 * 
	 * @param tipusok k�v�nt t�pusok
	 * @param ertekek k�v�nt �rt�kek
	 * @return {@code tipus} t�pus�, {@code ertek} �rt�k� objektum t�mb
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
	 * Megkeres �s visszaadja a cls oszt�ly vagy annak �s�ben tal�lhat� adattag_nev nev� adattagot.
	 * 
	 * @param cls A keresend� oszt�ly
	 * @param adattag_nev A keresend� adattag n�v
	 * @return A megtal�lt adattag. Ha nem l�tezik, nullal t�r vissza.
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
	 * L�trehoz egy tipus t�pus� objektumot az argumentumok felhaszn�l�s�val, majd elmenti az
	 * objektumok t�mbbe, id azonos�t�val. Ha nem tal�l megfelel� konstruktort, vagy nem tudja
	 * �talak�tani az argumentumokat megfelel� t�pusra vagy az id azonos�t� foglalt, nem hoz l�tre
	 * semmit.
	 * 
	 * @param tipus
	 * @param id
	 * @param argumentumok
	 */
	public static void letrehoz(String tipus, String id, String... argumentumok) {
		if (objektumok.containsKey(id)) {
			Log.error("A megadott azonos�t� m�r l�tezik! (" + id + ")");
			return;
		}
		try {
			Class<?> cls = Class.forName("src." + tipus);
			Constructor<?>[] constructors = cls.getDeclaredConstructors();
			// v�gign�zem a konstruktorokat
			for (Constructor<?> constructor : constructors) {
				Object[] tipusos_parameterek =
						tobbParameterTipusForditas(constructor.getParameterTypes(), argumentumok);

				if (tipusos_parameterek != null) { // A L�NYEG
					try {
						objektumok.put(id.toLowerCase(),
								constructor.newInstance(tipusos_parameterek));
						Log.info(id + " " + cls.getName() + " Sikeresen l�trehozva");
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
	 * Az id azonos�t�j� objektumon megh�vja a fuggveny_nev nev� f�ggv�nyt az argumentumok
	 * argumentumokkal. A visszat�r�si �rt�k a h�vott f�ggv�ny eredm�nye
	 * 
	 * @param id
	 * @param fuggveny_nev
	 * @param argumentumok
	 * @return
	 */
	public static Object hiv(String id, String fuggveny_nev, String... argumentumok) {
		if (!objektumok.containsKey(id)) {
			Log.error("Az azonos�t� nem l�tezik! (" + id + ")");
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
								"A f�ggv�nyh�v�s h�v�s nem siker�lt. A program inkonzisztens �llapotba ker�lhetett.");
						Log.debug(e.toString());
					}
				}
			}
		}
		Log.warn(fuggveny_nev + " nem tal�lhat�, vagy a megadott param�terek nem megfelel�ek. ("
				+ cls.getName() + " oszt�lyon h�vva).");
		return null;
	}

	/**
	 * Az id azonos�t�j� objektum adattag_neve nev� adattagj�t uj_ertek �rt�kre �ll�tja be, ha
	 * l�tezik az adattag, �s �talak�that� az uj_ertek az adattag t�pus�ra.
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
					+ ") be�ll�t�sa.");
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
		switch (Cin.kerdez_tobbvalasz("MEN�", "Mozg�s �rhaj�val", "Mozg�s teleport kapun kereszt�l",
				"B�ny�szat", "V�zj�g F�r�s", "Ur�n f�r�s", "F�r�s vas", "Port�lkapu �p�t�s",
				"Robot �p�t�s", "Nyersanyag visszahelyez�s", "Port�l lehelyez�s",
				"Robot Ur�n f�r�s", "Napvihar")) {
			// TODO

			default:
				break;
		}
	}

	public static void BanyaszatMenu() {
		switch (Cin.kerdez_tobbvalasz("B�NY�SZAT", "Ur�n B�ny�szat", "V�zj�g B�ny�szat",
				"Sz�n B�ny�szat", "Vas B�ny�szat")) {
			// TODO

			default:
				break;
		}
	}

	public static void NyersanyagVisszahelyezesMenu() {
		System.out.println("1. Ur�n visszahelyez�s\r\n" + "2. V�zj�g visszahelyez�s\r\n"
				+ "3. Sz�n visszahelyez�s\r\n" + "4. Vas visszahelyez�s");
		switch (Cin.kerdez_tobbvalasz("B�NY�SZAT", "Ur�n visszahelyez�s", "V�zj�g visszahelyez�s",
				"Sz�n visszahelyez�s", "Vas visszahelyez�s")) {
			// TODO

			default:
				break;
		}
	}

	static void teszt_letrehozPortalAszteroida(String... argumentumok) {
		letrehoz("Portal", argumentumok[0]);
		beallit(argumentumok[0], "aszteroida", argumentumok[1]);
	}

	static void teszt_osszekotAszteroida(String... argumentumok) {
		hiv(argumentumok[0], "hozzaadSzomszed", argumentumok[1]);
		hiv(argumentumok[1], "hozzaadSzomszed", argumentumok[0]);
	}

	static void teszt_osszekotPortal(String... argumentumok) {
		hiv(argumentumok[0], "beallitPar", argumentumok[1]);
		hiv(argumentumok[1], "beallitPar", argumentumok[0]);
	}

	static void teszt_epitRobot(String... argumentumok) {
		Robot r = (Robot) hiv(argumentumok[0], "epitRobot");
		if (r != null)
			objektumok.put(argumentumok[1], r);
	}

	static void teszt_banyaszas(String... argumentumok) {
		hiv(argumentumok[0], "Banyaszat");
	}

	static void teszt_letrehozNyersanyag(String... argumentumok) {
		letrehoz(argumentumok[1], argumentumok[0]);
	}

	static void teszt_letrehozAszteroida(String... argumentumok) {
		letrehoz("Aszteroida", argumentumok[0], "nap");
		hiv(argumentumok[0], "setReteg", argumentumok[1]);
		hiv(argumentumok[0], "setNapkozel", argumentumok[2]);
		hiv(argumentumok[0], "setNyersanyag", argumentumok[3]);
	}

	static void teszt_letrehozTelepes(String... argumentumok) {
		letrehoz("Telepes", argumentumok[0]);
		hiv(argumentumok[0], "beallitAszteroida", argumentumok[1]); // aszteroidan is rajta lesz a
																	// telepes
		String[] nyersanyagok = Arrays.copyOfRange(argumentumok, 2, argumentumok.length);
		for (int i = 0; i < nyersanyagok.length; i++) {
			hiv(argumentumok[0], "hozzaadNyersanyag", nyersanyagok[i]);
		}
	}

	// static void teszt_mozgas(String... argumentumok) {
	// hiv(argumentumok[0],"Mozgas",); //TODO daninak majd ejfelkor
	// }

}

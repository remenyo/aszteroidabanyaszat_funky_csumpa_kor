package src;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Map;

public class Szkeleton {
	// TODO ez már nem csak szkeleton, most már ez a "játék"

	public static final Szkeleton INSTANCE = new Szkeleton();

	private static Map<String, Object> objektumok;

	private static Map<String, Boolean> jatekosLepett;

	private Szkeleton() {
		objektumok = new TreeMap<>();
		objektumok.put("_this", INSTANCE);
		objektumok.put("jatek", Jatek.INSTANCE);
		objektumok.put("nap", new Nap());
		// TODO itt bele kell rakni a játék automatikusan létrehozott globális objektumait a tömbbe.
	}

	public void run() {
		int valasz = Cin.kerdez_tobbvalasz("Fõmenü", "Játék indítás", "Parancssor",
				"Teszt betöltés", "Kilépés");
		switch (valasz) {
			case 0:
				Jatek.jatekInditas();
				break;
			case 1:
				// parancssor
				teszt_parancssor();

			default:
				break;
		}
	};

	public static void teszt_parancssor() {
		while (true) {
			String parancs = Cin.getString("> ");
			if (parancs.toLowerCase().equals("kilepes")) {
				return;

			} else if (parancs.toLowerCase().equals("parancssor")) {
				Log.info("Már a parancssorban vagy.");
			} else {
				String[] argumentumok = parancs.split(":");
				if (argumentumok.length > 2)
					parancs(argumentumok[0],
							Arrays.copyOfRange(argumentumok, 1, argumentumok.length));
			}
		}
	}

	private static void parancs(String parancs, String... argumentumok) {
		try {
			if (Boolean.class.cast(hiv("_this", "teszt_" + parancs, argumentumok))) {
				Log.warn(
						"A parancs nem sikerült. A program lehet hogy inkonzisztens állapotba került.");
			}
		} catch (Exception e) {
			Log.error("Sikertelen parancshívás!");
		}
	}

	/**
	 * Visszaad egy tipus típusú objektumot, aminek az értéke az ertek. Ha létezik ertek
	 * azonosítóval tipus típusú objektum az objektumok tömbben, akkor azt az objektumot adja
	 * vissza. Ha nem sikeres az átalakítás, a függvény kivételt generál.
	 * 
	 * @param tipus A visszaadandó objektum típusa
	 * @param ertek Az objektum értéke
	 * @return {@code tipus} típusú, {@code ertek} értékû objektum
	 * @throws Exception Ha nem sikerül az átlakítás, kivételt generál a függvény.
	 */
	private static Object egyParameterTipusForditas(Class<?> tipus, String ertek) throws Exception {
		// van ilyen objektumunk tárolva?
		if (objektumok.containsKey(ertek.toLowerCase())) {
			if (tipus.isInstance(objektumok.get(ertek))) {
				// ünnepeljünk, megtaláltuk
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
				throw new Exception("Fordítás sikertelen", e);
			}
		}
	}

	/**
	 * Az ertekek tömb érékeit a tipus tömb szerinti típusú objektumokká alakítja, majd visszaadja
	 * õket egy tömbben. Hiba esetén null-al tér vissza.
	 * 
	 * @param tipusok kívánt típusok
	 * @param ertekek kívánt értékek
	 * @return {@code tipus} típusú, {@code ertek} értékû objektum tömb
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
	 * Megkeres és visszaadja a cls osztály vagy annak õsében található adattag_nev nevû adattagot.
	 * 
	 * @param cls A keresendõ osztály
	 * @param adattag_nev A keresendõ adattag név
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
	 * objektumok tömbbe, id azonosítóval. Ha nem talál megfelelõ konstruktort, vagy nem tudja
	 * átalakítani az argumentumokat megfelelõ típusra vagy az id azonosító foglalt, nem hoz létre
	 * semmit.
	 * 
	 * @param tipus
	 * @param id
	 * @param argumentumok
	 */
	public static void letrehoz(String tipus, String id, String... argumentumok) {
		if (objektumok.containsKey(id)) {
			Log.error("A megadott azonos˜t˜ m˜r l˜tezik! (" + id + ")");
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
	 * Az id azonosítójú objektumon meghívja a fuggveny_nev nevû függvényt az argumentumok
	 * argumentumokkal. A visszatérési érték a hívott függvény eredménye
	 * 
	 * @param id
	 * @param fuggveny_nev
	 * @param argumentumok
	 * @return
	 */
	public static Object hiv(String id, String fuggveny_nev, String... argumentumok) {
		if (!objektumok.containsKey(id)) {
			Log.error("A megadott azonosító nem létezik! (" + id + ")");
			return null;
		}
		Method[] fuggvenyek = objektumok.get(id).getClass().getMethods();
		for (Method fuggveny : fuggvenyek) {
			if (fuggveny.getName().equals(fuggveny_nev)) {
				Object[] tipusos_parameterek =
						tobbParameterTipusForditas(fuggveny.getParameterTypes(), argumentumok);
				if (tipusos_parameterek != null) {
					try {
						return fuggveny.invoke(objektumok.get(id), tipusos_parameterek);
					} catch (Exception e) {
						Log.debug(e.toString());
					}
				}
			}
		}
		return null;
	}

	/**
	 * Az id azonosítójú objektum adattag_neve nevû adattagját uj_ertek értékre állítja be, ha
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

	public static void Menu() {
		switch (Cin.kerdez_tobbvalasz("MENÜ", "Mozgás ûrhajóval", "Mozgás teleport kapun keresztül",
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
}

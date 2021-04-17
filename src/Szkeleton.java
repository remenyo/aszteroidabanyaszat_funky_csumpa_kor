package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

public class Szkeleton {

	private static final Szkeleton INSTANCE = new Szkeleton();

	public static Szkeleton getInstance() {
		return INSTANCE;
	}

	private static Map<String, Object> objektumok;
	private static ArrayList<String> filebaIrando = new ArrayList<String>();
	protected static Boolean inkonzisztens_allapot = false;
	protected static Boolean automata_futas = false;

	private Szkeleton() {
		objektumok = new TreeMap<>();
	}

	public static void Fomenu() {
		while (true) {
			int valasz = Cin.kerdez_tobbvalasz("Főmenü", "Játék indítás", "Parancssor",
					"Teszt betöltés", "Játék alaphelyzetbe állítása", "Kilépés");
			switch (valasz) {
				case 1:
					Jatek.jatekInditas();
					break;
				case 2:
					teszt_parancssor();
					break;
				case 3:
					teszt_betoltes(Cin.getString("A fájl neve/címe:"));
					break;
				case 4:
					teszt_reset();
					break;
				case 5:
					if (Cin.getBool("Ez törli a játék állapotát, biztos vagy benne?"))
						return;
				default:
					break;
			}
		}
	};

	public static void teszt_betoltes(String mappa_vagy_file) {
		automata_futas = true;
		List<File> files = Filekezelo.fajlListazas(mappa_vagy_file);
		Filekezelo.athelyez_regi_eredmenyek(files);
		for (File file : files) {
			Log.debug("Teszt " + file.getName());
			Filekezelo.auto_teszt_futtatas(file);
			if (inkonzisztens_allapot) {
				Log.warn("A teszt nem biztos hogy végig lefutott.");
				break;
			} else {
				Log.debug(file.getName() + " végig lefutott.");
			}
			// TODO automatikus ellenorzes
		}
		automata_futas = false;
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
				Log.info("Már a parancssorban vagy.");
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
		inkonzisztens_allapot = false;
		objektumok.clear();
		objektumok.put("_this", getInstance());
		objektumok.put("jatek", Jatek.getInstance());
		Jatek.beallitas_visszatoltes();
		objektumok.put("nap", new Nap());
		// TODO itt bele kell rakni a játék automatikusan létrehozott globális objektumait a
		// tömbbe.
		Log.info("RESET");
	}

	protected static void parancs(String parancs, String... argumentumok) {
		hiv("_this", "teszt_" + parancs, argumentumok);
	}

	/**
	 * Visszaad egy tipus tí­pusú objektumot, aminek az értéke az ertek. Ha létezik ertek
	 * azonosí­tóval tipus tí­pusú objektum az objektumok tömbben, akkor azt az objektumot adja
	 * vissza. Ha nem sikeres az átalakí­tás, a függvény kivételt generál.
	 * 
	 * @param tipus A visszaadandó objektum tí­pusa
	 * @param ertek Az objektum értéke
	 * @return {@code tipus} tí­pusú, {@code ertek} értékű objektum
	 * @throws Exception Ha nem sikerül az átlakí­tás, kivételt generál a függvény.
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
				throw new Exception("Fordí­tás sikertelen", e);
			}
		}
	}

	/**
	 * Az ertekek tömb érékeit a tipus tömb szerinti tí­pusú objektumokká alakí­tja, majd visszaadja
	 * őket egy tömbben. Hiba esetén null-al tér vissza.
	 * 
	 * @param tipusok kí­vánt tí­pusok
	 * @param ertekek kí­vánt értékek
	 * @return {@code tipus} tí­pusú, {@code ertek} értékű objektum tömb
	 */
	private static Object[] tobbParameterTipusForditas(Class<?>[] tipusok, String[] ertekek) {
		// String[] ertekek = new String[tipusok.length];
		// if (tipusok.length > 0 && tipusok[tipusok.length - 1].isArray()) {
		// for (int i = 0; i < tipusok.length - 1; i++) {
		// ertekek[i] = nyers_ertekek[i];
		// }
		//
		// }
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
	 * Létrehoz egy tipus tí­pusú objektumot az argumentumok felhasználásával, majd elmenti az
	 * objektumok tömbbe, id azonosí­tóval. Ha nem talál megfelelő konstruktort, vagy nem tudja
	 * átalakí­tani az argumentumokat megfelelő tí­pusra vagy az id azonosí­tó foglalt, nem hoz
	 * létre semmit.
	 * 
	 * @param tipus
	 * @param id
	 * @param argumentumok
	 */
	public static void letrehoz(String tipus, String id, String... argumentumok) {
		if (objektumok.containsKey(id)) {
			Log.error("A megadott azonosí­tó már létezik! (" + id + ")");
			inkonzisztencia();
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
	 * Az id azonosí­tójú objektumon meghí­vja a fuggveny_nev nevű függvényt az argumentumok
	 * argumentumokkal. A visszatérési érték a hí­vott függvény eredménye
	 * 
	 * @param id
	 * @param fuggveny_nev
	 * @param argumentumok
	 * @return
	 */
	public static Object hiv(String id, String fuggveny_nev, String... argumentumok) {
		if (!objektumok.containsKey(id)) {
			Log.error("Az azonosí­tó nem létezik! (" + id + ")");
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
								"A függvényhí­vás hí­vás nem sikerült. A program inkonzisztens állapotba kerülhetett.");
						inkonzisztencia();
						Log.debug(e.toString());
					}
				}
			}
		}
		Log.warn(fuggveny_nev + " nem található, vagy a megadott paraméterek nem megfelelőek. ("
				+ cls.getName() + " osztályon hí­vva).");
		return null;
	}

	/**
	 * Az id azonosí­tójú objektum adattag_neve nevű adattagját uj_ertek értékre állí­tja be, ha
	 * létezik az adattag, és átalakí­tható az uj_ertek az adattag tí­pusára.
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
					+ ") beállí­tása.");
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
			return id + "-nek (" + className(id) + ") nincs toString() függvénye.";
		}
	}

	private static String className(String id) {
		try {
			return objektumok.get(id).getClass().getName();
		} catch (Exception e) {
			Log.error(e.toString());
			return "?";
		}
	}

	public static Object getObj(String id) {
		return objektumok.get(id);
	}

	public static String getID(Object object) {
		for (Entry<String, Object> e : objektumok.entrySet()) {
			if (e.getValue().equals(object)) {
				return e.getKey();
			}
		}
		return "<ismeretlen " + object.getClass().getName() + ">";
	}

	private static void inkonzisztencia() {
		if (automata_futas) {
			inkonzisztens_allapot = true;
		}
	}

	public static void JatekMenu() {
		switch (Cin.kerdez_tobbvalasz("MENÜ", "Mozgás űrhajóval", "Mozgás teleport kapun keresztül",
				"Bányászat", "Ví­zjég Fúrás", "Urán fúrás", "Fúrás vas", "Portálkapu épí­tés",
				"Robot épí­tés", "Nyersanyag visszahelyezés", "Portál lehelyezés",
				"Robot Urán fúrás", "Napvihar")) {
			// TODO

			default:
				break;
		}
	}

	public static void BanyaszatMenu() {
		switch (Cin.kerdez_tobbvalasz("Bí?NYí?SZAT", "Urán Bányászat", "Ví­zjég Bányászat",
				"Szén Bányászat", "Vas Bányászat")) {
			// TODO

			default:
				break;
		}
	}

	public static void NyersanyagVisszahelyezesMenu() {
		System.out.println("1. Urán visszahelyezés\r\n" + "2. Ví­zjég visszahelyezés\r\n"
				+ "3. Szén visszahelyezés\r\n" + "4. Vas visszahelyezés");
		switch (Cin.kerdez_tobbvalasz("Bí?NYí?SZAT", "Urán visszahelyezés",
				"Ví­zjég visszahelyezés", "Szén visszahelyezés", "Vas visszahelyezés")) {
			// TODO

			default:
				break;
		}
	}

	public static void teszt_reset() {
		if (Cin.getBool(
				"A program minden beállí­tása alapértelmezett értékre áll vissza, és minden létrehozott objektum törlődik. Biztos vagy benne?")) {
			reset();
		}
	}

	public static void teszt_letrehozPortalAszteroida(String pid, String aid) {
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
		if (lepesTeszt(tid)) {
			Robot r = (Robot) hiv(tid, "epitRobot");
			if (r != null)
				objektumok.put(rid, r);
		}
	}

	public static void teszt_banyaszas(String tid) {
		if (lepesTeszt(tid)) {
			hiv(tid, "Banyaszat");
		}
	}

	public static String Nagykezdobetusites(String szo) {
		return ((szo.charAt(0) + "").toUpperCase() + szo.substring(1));
	}

	public static void teszt_letrehozNyersanyag(String nyid, String tipus) {
		letrehoz(Nagykezdobetusites(tipus), nyid);
	}

	public static void teszt_letrehozAszteroida(String aid, String reteg, String napkozel,
			String nyid) {
		letrehoz("Aszteroida", aid, "nap");
		hiv(aid, "setReteg", reteg);
		hiv(aid, "setNapkozel", napkozel);
		if (!nyid.equals("null"))
			hiv(aid, "setNyersanyag", nyid);
	}

	public static void teszt_letrehozTelepes(String tid, String aid, String nyids) {
		letrehoz("Telepes", tid);
		hiv(tid, "beallitAszteroida", aid); // aszteroidan is rajta lesz a
											// telepes
		// String[] nyersanyagok = Arrays.copyOfRange(argumentumok, 2, argumentumok.length);
		if (nyids.equals("null"))
			return;
		String[] nyidsDarabolt = nyids.split(",");
		for (int i = 0; i < nyidsDarabolt.length; i++) {
			hiv(tid, "hozzaadNyersanyag", nyidsDarabolt[i]);
		}
	}

	public static void teszt_mozgas(String id, String aid) {
		if (lepesTeszt(id)) {
			Aszteroida aminVagyunk = (Aszteroida) hiv(id, "getAszteroida");
			Aszteroida amireMegyunk = ((Aszteroida) getObj(aid));
			Integer menesSzam = aminVagyunk.getSzomszedok().indexOf(amireMegyunk);
			if (menesSzam != -1) {
				hiv(id, "Mozgas", menesSzam.toString()); // TODO ez itt igy jo?
			}
		}

	}

	public static void teszt_info(String id) {
		String result = info(id);
		Log.info(result);
		filebaIrando.add(result);
	}

	public static void teszt_mentes(String nev) {
		// TODO beirni 0. fejezetbe hogy ne irjak oda hogy .txt

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
			Log.error(e.toString());
		}
	}

	public static void teszt_infoMinden() {
		for (String id : objektumok.keySet()) {
			Log.info(info(id));
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

	public static void mindenkiLepett() {// FONTOS
		if (Jatek.mindenkiLepett())
			Jatek.resetLepett();
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
		if (((Telepes) getObj(tid)).getPortal().size() < 3) {
			letrehoz("Portal", pid);
			hiv(pid, "setBirtokos", tid);
			hiv(tid, "setPortal", pid);
		}
	}

	public static void teszt_visszarakNyersanyag(String tid, String nyid) {
		if (lepesTeszt(tid)) {
			hiv(tid, "visszarakNyersanyag", nyid);
		}
	}

	public static Boolean lepesTeszt(String id) {
		if ((Boolean) hiv(id, "lepette") == false) {
			mindenkiLepett();
			return true; // léphet
		}
		Log.warn("Már lépett!");
		inkonzisztencia();
		return false;
	}


	public static void teszt_lerakPortal(String tid, String pid) {
		if (lepesTeszt(tid)) {
			hiv(tid, "lerakPortal", pid);
		}
	}

	public static void teszt_epitPortal(String tid, String pid1, String pid2) {
		if (lepesTeszt(tid)) {
			ArrayList<Portal> portalok = (ArrayList<Portal>) hiv(tid, "epitPortal");
			if (portalok != null) {
				objektumok.put(pid1, portalok.get(0));
				objektumok.put(pid2, portalok.get(1));
			}
		}
	}

	public static void teszt_furas(String id) {
		if (lepesTeszt(id)) {
			hiv(id, "Furas");
		}
	}

	public static void teszt_napviharOkozasa(String aids) { // hehe
		if (aids.equals("null"))
			return;
		String[] aidsDarabolt = aids.split(",");
		for (int i = 0; i < aidsDarabolt.length; i++) {
			hiv(aidsDarabolt[i], "Napvihar");
		}
	}

<<<<<<< Updated upstream
	public static void teszt_randomValoszinuseg(String igazsagErtek) {
		beallit("jatek", "robot_robbanas_elso_szomszed", igazsagErtek);
=======
	public static void teszt_randomValoszinuseg(String nev,String igazsagErtek) {
		((Jatek)objektumok.get("jatek")).robot_robbanas_elso_szomszed = Boolean.parseBoolean(igazsagErtek);
>>>>>>> Stashed changes
	}
}

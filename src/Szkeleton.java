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

public class Szkeleton {

	private static final Szkeleton INSTANCE = new Szkeleton();

	public static Szkeleton getInstance() {
		return INSTANCE;
	}

	private static Map<String, Object> objektumok;
	private static ArrayList<String> filebaIrando = new ArrayList<String>();
	private static Integer robot_id = 0;

	protected static Boolean inkonzisztens_allapot = false;
	protected static Boolean automata_futas = false;

	public static String dir_to_save;

	private Szkeleton() {
		objektumok = new TreeMap<>();
	}

	/**
	 * Robot létrehozáskor ha regisztrálni szeretnénk a robotot, itt kérhetünk hozzá egyedi id-t.
	 * 
	 * @return robot azonosító
	 */
	public static Integer getRobotID() {
		return robot_id++;
	}

	public static void Fomenu() {
		while (true) {
			int valasz = Cin.kerdez_tobbvalasz("FĹ‘menĂĽ", "JĂˇtĂ©k indĂ­tĂˇs", "Parancssor",
					"Teszt betĂ¶ltĂ©s", "JĂˇtĂ©k alaphelyzetbe ĂˇllĂ­tĂˇsa", "KilĂ©pĂ©s");
			switch (valasz) {
				case 1:
					Jatek.jatekInditas(objektumok.size() == 3);
					break;
				case 2:
					teszt_parancssor();
					break;
				case 3:
					teszt_betoltes(Cin.getString("A fĂˇjl neve/cĂ­me:"));
					break;
				case 4:
					teszt_reset();
					break;
				case 5:
					if (Cin.getBool("Ez tĂ¶rli a jĂˇtĂ©k ĂˇllapotĂˇt, biztos vagy benne?"))
						return;
				default:
					break;
			}
		}
	};

	/**
	 * Reseteli a jelenlegi Állapotot vissza ĂˇllĂ­tva a betĂ¶ltĂ©si Ăˇllapotba
	 * 
	 * @param ny beállítandó nyersanyag
	 */
	protected static void reset() {
		inkonzisztens_allapot = false;
		objektumok.clear();
		objektumok.put("_this", getInstance());
		objektumok.put("jatek", Jatek.getInstance());
		Jatek.reset();
		Vas.reset();
		Uran.reset();
		Vizjeg.reset();
		Szen.reset();
		objektumok.put("nap", new Nap());
		Log.info("RESET");
	}

	protected static void parancs(String parancs, String... argumentumok) {
		hiv("_this", "teszt_" + parancs, argumentumok);
	}

	/**
	 * Visszaad egy tipus tĂ­pusĂş objektumot, aminek az Ă©rtĂ©ke az ertek. Ha lĂ©tezik ertek
	 * azonosĂ­Â­tĂłval tipus tĂ­pusĂş objektum az objektumok tĂ¶mbben, akkor azt az objektumot adja
	 * vissza. Ha nem sikeres az ĂˇtalakĂ­Â­tĂˇs, a fĂĽggvĂ©ny kivĂ©telt generĂˇl.
	 * 
	 * @param tipus A visszaadandĂł objektum tĂ­Â­pusa
	 * @param ertek Az objektum Ă©rtĂ©ke
	 * @return {@code tipus} tĂ­Â­pusĂş, {@code ertek} Ă©rtĂ©kĹ± objektum
	 * @throws Exception Ha nem sikerĂĽl az ĂˇtlakĂ­Â­tĂˇs, kivĂ©telt generĂˇl a fĂĽggvĂ©ny.
	 */
	private static Object egyParameterTipusForditas(Class<?> tipus, String ertek) throws Exception {
		// van ilyen objektumunk tárolva?
		if (objektumok.containsKey(ertek)) {
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
				throw new Exception("FordĂ­Â­tĂˇs sikertelen", e);
			}
		}
	}

	/**
	 * Az ertekek tömb értékeit a tipus tömb szerinti típus objektumokká alakítja, majd visszaadja
	 * őket egy tömbben. Hiba esetén null-al tér vissza.
	 * 
	 * @param tipusok kívánt típusok
	 * @param ertekek kívánt értékek
	 * @return {@code tipus} típusú, {@code ertek} érékű objektum tömb.
	 */
	private static Object[] tobbParameterTipusForditas(Class<?>[] tipusok, String[] ertekek) {
		// a varargok kifogtak rajtam...
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
	 * Megkeres Ă©s visszaadja a cls osztĂˇly vagy annak Ĺ‘sĂ©ben talĂˇlhatĂł adattag_nev nevĹ±
	 * adattagot.
	 * 
	 * @param cls A keresendĹ‘ osztĂˇly
	 * @param adattag_nev A keresendĹ‘ adattag nĂ©v
	 * @return A megtalĂˇlt adattag. Ha nem lĂ©tezik, nullal tĂ©r vissza.
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
	 * LĂ©trehoz egy tipus tĂ­Â­pusĂş objektumot az argumentumok felhasznĂˇlĂˇsĂˇval, majd elmenti
	 * az objektumok tĂ¶mbbe, id azonosĂ­Â­tĂłval. Ha nem talĂˇl megfelelĹ‘ konstruktort, vagy nem
	 * tudja ĂˇtalakĂ­Â­tani az argumentumokat megfelelĹ‘ tĂ­Â­pusra vagy az id azonosĂ­Â­tĂł
	 * foglalt, nem hoz lĂ©tre semmit.
	 * 
	 * @param tipus
	 * @param id
	 * @param argumentumok
	 * 
	 * @return Boolean a fĂĽggvĂ©ny sikeressĂ©gĂ©t jelzi.
	 */
	public static Boolean letrehoz(String tipus, String id, String... argumentumok) {
		if (objektumok.containsKey(id)) {
			Log.error("A megadott azonosító már létezik! (" + id + ")");
			inkonzisztencia();
			return false;
		}
		try {
			Class<?> cls = Class.forName("src." + tipus);
			Constructor<?>[] constructors = cls.getDeclaredConstructors();
			// vĂ©gignĂ©zem a konstruktorokat
			for (Constructor<?> constructor : constructors) {
				Object[] tipusos_parameterek =
						tobbParameterTipusForditas(constructor.getParameterTypes(), argumentumok);

				if (tipusos_parameterek != null) { // A LĂ‰NYEG
					try {
						objektumok.put(id, constructor.newInstance(tipusos_parameterek));
						Log.info(id + " " + cls.getName() + " Sikeresen lĂ©trehozva");
						return true;
					} catch (Exception e) {
						inkonzisztencia();
						Log.error(e.toString());
					}
				}
			}
		} catch (Exception e) {
			inkonzisztencia();
			Log.error(e.toString());
		}
		return false;
	}

	/**
	 * Az id azonosĂ­Â­tĂłjĂş objektumon meghĂ­Â­vja a fuggveny_nev nevĹ± fĂĽggvĂ©nyt az
	 * argumentumok argumentumokkal. A visszatĂ©rĂ©si Ă©rtĂ©k a hĂ­Â­vott fĂĽggvĂ©ny eredmĂ©nye
	 * 
	 * @param id
	 * @param fuggveny_nev
	 * @param argumentumok
	 * 
	 * @return A hĂ­vott fĂĽggvĂ©ny visszatĂ©rĂ©si Ă©rtĂ©ke.
	 */
	public static Object hiv(String id, String fuggveny_nev, String... argumentumok) {
		if (!objektumok.containsKey(id)) {
			Log.error("Az azonosĂ­Â­tĂł nem lĂ©tezik! (" + id + ")");
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
								"A fĂĽggvĂ©nyhĂ­Â­vĂˇs hĂ­Â­vĂˇs nem sikerĂĽlt. A program inkonzisztens Ăˇllapotba kerĂĽlhetett.");
						inkonzisztencia();
						Log.debug(e.toString());
					}
				}
			}
		}
		Log.warn(fuggveny_nev + " nem talĂˇlhatĂł, vagy a megadott paramĂ©terek nem megfelelĹ‘ek. ("
				+ cls.getName() + " osztĂˇlyon hĂ­Â­vva).");
		return null;
	}

	/**
	 * Az id azonosĂ­Â­tĂłjĂş objektum adattag_neve nevĹ± adattagjĂˇt uj_ertek Ă©rtĂ©kre ĂˇllĂ­Â­tja
	 * be, ha lĂ©tezik az adattag, Ă©s ĂˇtalakĂ­Â­thatĂł az uj_ertek az adattag tĂ­Â­pusĂˇra.
	 * 
	 * @param id
	 * @param adattag_neve
	 * @param uj_ertek
	 * 
	 * @return Boolean a fĂĽggvĂ©ny sikeressĂ©gĂ©t jelzi.
	 */
	public static Boolean beallit(String id, String adattag_neve, String uj_ertek) {
		Boolean sikeres = false;
		Field adattag = adattagKereses(objektumok.get(id).getClass(), adattag_neve);
		int modifier = adattag.getModifiers();
		boolean private_field = Modifier.isPrivate(modifier);
		if (private_field) {
			Log.warn(Modifier.toString(modifier) + " adattag (" + adattag.getName()
					+ ") beĂˇllĂ­Â­tĂˇsa.");
			adattag.setAccessible(true);
		}

		if (adattag != null) {
			try {
				Object beallitando_ertek = egyParameterTipusForditas(adattag.getType(), uj_ertek);
				adattag.set(objektumok.get(id), beallitando_ertek);
				sikeres = true;
			} catch (Exception e) {
				inkonzisztencia();
				Log.error(e.toString());
			}
		}
		if (private_field) {
			adattag.setAccessible(private_field);
		}
		return sikeres;
	}

	/**
	 * InformĂˇciĂł az adott azonosĂ­tĂłjĂş objektumrĂłl
	 * 
	 * @param id - objektum azonosĂ­tĂłja
	 * @return - informĂˇciĂł
	 */
	public static String info(String id) {
		try {
			return String.class.cast(hiv(id, "toString"));
		} catch (Exception e) {
			Log.error(e.toString());
			return id + "-nek (" + className(id) + ") nincs toString() fĂĽggvĂ©nye.";
		}
	}

	private static String className(String id) {
		try {
			return objektumok.get(id).getClass().getName().replaceAll("src.", "");
		} catch (Exception e) {
			Log.error(e.toString());
			return "?";
		}
	}

	/**
	 * Objektum lekĂ©rdezĂ©se azonosĂ­tĂłbĂłl
	 * 
	 * @param id - azonosĂ­tĂł
	 * @return - megfelelĹ‘ objektum
	 */
	public static Object getObj(String id) {
		return objektumok.get(id);
	}

	/**
	 * AzonosĂ­tĂł lekĂ©rdezĂ©se
	 * 
	 * @param object - objektum, aminek az azonosĂ­tĂłjĂˇt lekĂ©rdezzĂĽk
	 * @return - azonosĂ­tĂł
	 */
	public static String getID(Object object) {
		for (Entry<String, Object> e : objektumok.entrySet()) {
			if (e.getValue().equals(object)) {
				return e.getKey();
			}
		}
		return "<ismeretlen " + object.getClass().getName().replaceAll("src.", "") + ">";
	}

	/**
	 * Inkonzisztens Ăˇllapotba kerĂĽlĂ©s
	 */
	private static void inkonzisztencia() {
		if (automata_futas) {
			inkonzisztens_allapot = true;
		}
	}

	/**
	 * JĂˇtĂ©k menĂĽ mĹ±veletek
	 */
	public static void JatekMenu() {
		switch (Cin.kerdez_tobbvalasz("MENĂś", "MozgĂˇs Ĺ±rhajĂłval",
				"MozgĂˇs teleport kapun keresztĂĽl", "BĂˇnyĂˇszat", "VĂ­Â­zjĂ©g FĂşrĂˇs",
				"UrĂˇn fĂşrĂˇs", "FĂşrĂˇs vas", "PortĂˇlkapu Ă©pĂ­Â­tĂ©s", "Robot Ă©pĂ­Â­tĂ©s",
				"Nyersanyag visszahelyezĂ©s", "PortĂˇl lehelyezĂ©s", "Robot UrĂˇn fĂşrĂˇs",
				"Napvihar")) {
			// TODO ez kell mĂ©g?

			default:
				break;
		}
	}

	/**
	 * BĂˇnyĂˇszat kivĂˇlasztĂˇsa
	 */
	public static void BanyaszatMenu() {
		switch (Cin.kerdez_tobbvalasz("BĂ­?NYĂ­?SZAT", "UrĂˇn BĂˇnyĂˇszat",
				"VĂ­Â­zjĂ©g BĂˇnyĂˇszat", "SzĂ©n BĂˇnyĂˇszat", "Vas BĂˇnyĂˇszat")) {
			// TODO ez kell mĂ©g?

			default:
				break;
		}
	}

	/**
	 * VisszahelyezendĹ‘ nyersanyag kivĂˇlasztĂˇsa
	 */
	public static void NyersanyagVisszahelyezesMenu() {
		System.out.println("1. UrĂˇn visszahelyezĂ©s\r\n" + "2. VĂ­Â­zjĂ©g visszahelyezĂ©s\r\n"
				+ "3. SzĂ©n visszahelyezĂ©s\r\n" + "4. Vas visszahelyezĂ©s");
		switch (Cin.kerdez_tobbvalasz("BĂ�NYĂ�SZAT", "UrĂˇn visszahelyezĂ©s",
				"VĂ­Â­zjĂ©g visszahelyezĂ©s", "SzĂ©n visszahelyezĂ©s", "Vas visszahelyezĂ©s")) {
			// TODO ez kell mĂ©g?

			default:
				break;
		}
	}

	/**
	 * Tesztek betĂ¶ltĂ©se
	 * 
	 * @param mappa_vagy_file - mappa vagy file ahonnan a betĂ¶ltĂ©s tĂ¶rtĂ©nik
	 */
	public static void teszt_betoltes(String mappa_vagy_file) {
		automata_futas = true;
		List<File> files = Filekezelo.fajlListazas(mappa_vagy_file);
		Filekezelo.athelyez_regi_eredmenyek(files);
		for (File file : files) {
			reset();
			Log.debug("Teszt " + file.getName());
			Filekezelo.auto_teszt_futtatas(file);
			if (!automata_futas && inkonzisztens_allapot) {
				Log.warn("A teszt nem biztos hogy vĂ©gig lefutott.");
				break;
			} else {
				Log.debug(file.getName() + " vĂ©gig lefutott.");
				teszt_mentes(dir_to_save + "\\" + file.getName().replace(".txt", ""));
			}
		}
		automata_futas = false;
	}

	/**
	 * Parancssor mĹ±kĂ¶dĂ©se
	 */
	public static void teszt_parancssor() {
		dir_to_save = "";
		while (true) {
			System.out.print("> ");
			String parancs = Cin.getString();
			parancs = parancs.replaceAll("\\s+", "");
			if (parancs.equals(""))
				continue;
			if (parancs.toLowerCase().equals("kilepes")) {
				return;
			} else if (parancs.toLowerCase().equals("parancssor")) {
				Log.info("MĂˇr a parancssorban vagy.");
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

	/**
	 * Minden parancs kĂ¶vetkezmĂ©nyĂ©nek elvetĂ©se
	 */
	public static void teszt_reset() {
		if (automata_futas || Cin.getBool(
				"A program minden beĂˇllĂ­Â­tĂˇsa alapĂ©rtelmezett Ă©rtĂ©kre Ăˇll vissza, Ă©s minden lĂ©trehozott objektum tĂ¶rlĹ‘dik. Biztos vagy benne?")) {
			reset();
		}
	}

	/**
	 * PortĂˇl lĂ©trehozĂˇsa aszteroidĂˇn
	 * 
	 * @param pid - portĂˇl azonosĂ­tĂłja
	 * @param aid - aszteroida azonosĂ­tĂłja
	 */
	public static void teszt_letrehozPortalAszteroida(String pid, String aid) {
		if (!letrehoz("Portal", pid))
			return;
		hiv(pid, "setVegpont", aid);
		hiv(aid, "hozzaadSzomszed", pid);
	}

	/**
	 * AszteroidĂˇk szomszĂ©djĂˇnak beĂˇllĂ­tĂˇsa
	 * 
	 * @param aid1 - elsĹ‘ aszteroida azonosĂ­tĂłja
	 * @param aid2 - mĂˇsoik aszteroida azonosĂ­tĂłja
	 */
	public static void teszt_osszekotAszteroida(String aid1, String aid2) {
		hiv(aid1, "hozzaadSzomszed", aid2);
		hiv(aid2, "hozzaadSzomszed", aid1);
	}

	/**
	 * PortĂˇlpĂˇrok beĂˇllĂ­tĂˇsa
	 * 
	 * @param pid1 - elsĹ‘ portĂˇl azonosĂ­tĂłja
	 * @param pid2 - mĂˇsoik portĂˇl azonosĂ­tĂłja
	 */
	public static void teszt_osszekotPortal(String pid1, String pid2) {
		hiv(pid1, "beallitPar", pid2);
		hiv(pid2, "beallitPar", pid1);
		Aszteroida p1vegpont = (Aszteroida) hiv(pid1, "getVegpont");
		Aszteroida p2vegpont = (Aszteroida) hiv(pid2, "getVegpont");
		if ((p1vegpont != null) && (p2vegpont != null)) {
			hiv(pid1, "setAktiv", "true");
			hiv(pid2, "setAktiv", "true");
		}
	}

	/**
	 * Robot Ă©pĂ­tĂ©se
	 * 
	 * @param tid - telepes azonosĂ­tĂłja, aki Ă©pĂ­ti a robotot
	 * @param rid - Robot azonosĂ­tĂłja
	 */
	public static void teszt_epitRobot(String tid, String rid) {
		if (lepesTeszt(tid)) {
			Robot r = (Robot) hiv(tid, "epitRobot");
			if (r != null)
				objektumok.put(rid, r);
		}
	}

	/**
	 * BĂˇnyĂˇszĂˇs tesztelĂ©se
	 * 
	 * @param tid - telepes azonosĂ­tĂłja, aki bĂˇnyĂˇszik
	 */
	public static void teszt_banyaszas(String tid) {
		if (lepesTeszt(tid)) {
			hiv(tid, "Banyaszat");
		}
	}

	/**
	 * SzĂł nagykezdĹ‘betĹ±betĹ±sĂ­tĂ©se
	 * 
	 * @param szo - ĂˇtalakĂ­tandĂł szĂł
	 * @return - ĂˇtalakĂ­tott szĂł
	 */
	public static String Nagykezdobetusites(String szo) {
		return ((szo.charAt(0) + "").toUpperCase() + szo.substring(1));
	}

	/**
	 * Nyersanyag lĂ©trehozĂˇsa
	 * 
	 * @param nyid - nyersanyag azonosĂ­tĂłja
	 * @param tipus - nyersanyag tĂ­pusa
	 */
	public static void teszt_letrehozNyersanyag(String nyid, String tipus) {
		if (!letrehoz(Nagykezdobetusites(tipus), nyid))
			return;
	}

	/**
	 * Aszteroida lĂ©trehozĂˇsa
	 * 
	 * @param aid - Aszteroida azonosĂ­tĂłja
	 * @param reteg - Aszteroida rĂ©tege
	 * @param napkozel - NapkĂ¶zel vagy naptĂˇvol
	 * @param nyid - nyersanyag, ami az aszteroidĂˇban van
	 */
	public static void teszt_letrehozAszteroida(String aid, String reteg, String napkozel,
			String nyid) {
		if (!letrehoz("Aszteroida", aid, "nap"))
			return;
		hiv(aid, "setReteg", reteg);
		hiv(aid, "setNapkozel", napkozel);
		if (!nyid.equals("null"))
			hiv(aid, "setNyersanyag", nyid);
	}

	/**
	 * Telepes lĂ©trehozĂˇsa
	 * 
	 * @param tid - telepes azonosĂ­tĂłja
	 * @param aid - aszteroida azonosĂ­tĂłja, ahova a telepes kerĂĽl
	 * @param nyids - nyersanyag azonosĂ­tĂłja, ami a telepesnĂ©l lesz
	 */
	public static void teszt_letrehozTelepes(String tid, String aid, String nyids) {
		if (!letrehoz("Telepes", tid))
			return;
		hiv(tid, "beallitAszteroida", aid); // aszteroidan is rajta lesz a
											// telepes
		String[] nyidsDarabolt = nyids.split(",");
		for (int i = 0; i < nyidsDarabolt.length; i++) {
			hiv(tid, "hozzaadNyersanyag", nyidsDarabolt[i]);
		}
	}

	/**
	 * Adott azonosĂ­tĂłjĂş objektum mozgatĂˇsa egy helyre.
	 * 
	 * @param id - objektum, ami mozog
	 * @param aid - hely, ahova mozog az objektum
	 */
	public static void teszt_mozgas(String id, String aid) {
		if (lepesTeszt(id)) {
			Aszteroida aminVagyunk = (Aszteroida) hiv(id, "getAszteroida");
			Hely amireMegyunk = ((Hely) getObj(aid));
			Integer menesSzam = aminVagyunk.getSzomszedok().indexOf(amireMegyunk);
			if (menesSzam != -1) {
				hiv(id, "Mozgas", menesSzam.toString()); // TODO ez itt igy jo?
			}
		}

	}

	/**
	 * Adott azonosĂ­tĂłjĂş objektum informĂˇciĂłjĂˇnak kiiratĂˇsa
	 * 
	 * @param id - objektum azonosĂ­tĂłja
	 */
	public static void teszt_info(String id) {
		String result = info(id);
		Log.info(result);
		filebaIrando.add(result);
	}

	/**
	 * Program kimenetĂ©nek lementĂ©se 'nev'_eredmeny nevĹ± jegyzettĂ¶mbbe
	 * 
	 * @param nev - ez lesz a file elsĹ‘ felĂ©nek neve
	 * @return - sikeressĂ©gĂ©rtĂ©k
	 */
	public static Boolean teszt_mentes(String nev) {
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
			Log.debug(nev + "_eredmeny.txt sikeresen mentve.");
			return true;
		} catch (Exception e) {
			Log.error(e.toString());
		}
		return false;
	}

	/**
	 * Minden informĂˇciĂł kilogolĂˇsa
	 */
	public static void teszt_infoMinden() {
		for (String id : objektumok.keySet()) {
			if (!id.startsWith("_")) {
				Log.info("[" + id + "]: " + info(id));
			}
		}
	}

	/**
	 * JĂˇtĂ©k ĂˇllapotĂˇnak lekĂ©rĂ©se
	 */
	public static void teszt_infoAllapot() {
		Integer jelenlegiAllapot = ((Integer) hiv("jatek", "getAllapot"));
		if (jelenlegiAllapot == 0) {
			Log.info("folyamatban");
			filebaIrando.add("folyamatban");
		} else if (jelenlegiAllapot == 1) {
			Log.info("nyert");
			filebaIrando.add("nyert");
		} else if (jelenlegiAllapot == -1) {
			Log.info("vesztett");
			filebaIrando.add("vesztett");
		}

	}

	/**
	 * Ăšj kĂ¶r indĂ­tĂˇsa
	 */
	public static void mindenkiLepett() {
		if (Jatek.mindenkiLepett())
			Jatek.resetLepett();
	}

	/**
	 * Robot lĂ©trehozĂˇsa aszteroidĂˇra
	 * 
	 * @param rid - robot azonosĂ­tĂłja
	 * @param aid - Aszteroida azonosĂ­tĂłja, ahova a robot kerĂĽlni fog
	 */
	public static void teszt_letrehozRobot(String rid, String aid) {
		if (!letrehoz("Robot", rid))
			return;
		hiv(rid, "beallitAszteroida", aid);
	}

	/**
	 * UfĂł lĂ©trehozĂˇsa eg aszteroidĂˇra
	 * 
	 * @param uid - ufĂł azonosĂ­tĂłja
	 * @param aid - Aszteroida azonosĂ­tĂłja, ahova az ufĂł kerĂĽlni fog
	 */
	public static void teszt_letrehozUfo(String uid, String aid) {
		if (!letrehoz("Ufo", uid))
			return;
		hiv(uid, "beallitAszteroida", aid);
	}

	/**
	 * PortĂˇl lĂ©trehozĂˇsa a telepes Ĺ±rhajĂłjĂˇba
	 * 
	 * @param pid - LĂ©trehozandĂł portĂˇl azonosĂ­tĂłja
	 * @param tid - Telepes, akihez a portĂˇl kerĂĽlni fog
	 */
	public static void teszt_letrehozPortalTelepes(String pid, String tid) {
		if (((Telepes) getObj(tid)).getPortal().size() < 3) {
			if (!letrehoz("Portal", pid))
				return;
			hiv(pid, "setBirtokos", tid);
			hiv(tid, "setPortal", pid);
		}
	}

	/**
	 * Nyersanyag visszarakĂˇsĂˇnak tesztelĂ©se
	 * 
	 * @param tid - telepes, aki visszarakja a nyersanyagot
	 * @param nyid - visszarakandĂł nyersanyag
	 */
	public static void teszt_visszarakNyersanyag(String tid, String nyid) {
		if (lepesTeszt(tid)) {
			// if(((Telepes) getObj(tid)).getNyersanyagok().size()>0) //itt talĂˇn nem
			// kellene
			// ellenĹ‘rizni, mert a jĂˇtĂ©k mĹ±kĂ¶dĂ©se nem itt zajlik
			hiv(tid, "visszarakNyersanyag", nyid);
		}
	}

	/**
	 * LĂ©ptethetĹ‘k lĂ©pĂ©sĂ©nek tesztelĂ©se
	 * 
	 * @param id - lĂ©ptethetĹ‘ azonosĂ­tĂłja
	 * @return - sikeres e a lĂ©pĂ©s
	 */
	public static Boolean lepesTeszt(String id) {
		if ((Boolean) hiv(id, "lepette") == false) {
			beallit(id, "lepett", "true");
			mindenkiLepett();
			return true; // lĂ©phet
		}
		Log.warn("MĂˇr lĂ©pett!");
		inkonzisztencia();
		return false;
	}

	/**
	 * PortĂˇl lerakĂˇsĂˇnak tesztelĂ©se
	 * 
	 * @param tid - telepes, aki lerakja a portĂˇlt
	 * @param pid - lerakandĂł portĂˇl zonosĂ­tĂłja
	 */
	public static void teszt_lerakPortal(String tid, String pid) {
		if (lepesTeszt(tid)) {
			hiv(tid, "lerakPortal", pid);
		}
	}

	/**
	 * PortĂˇlĂ©pĂ­tĂ©s tesztelĂ©se
	 * 
	 * @param tid - telepes Ă©pĂ­ti a portĂˇlt
	 * @param pid1 - elsĹ‘ Ă©pĂ­tendĹ‘ portĂˇl azonosĂ­tĂłja
	 * @param pid2 - mĂˇsodik Ă©pĂ­tendĹ‘ portĂˇl azonosĂ­tĂłja
	 */
	public static void teszt_epitPortal(String tid, String pid1, String pid2) {
		if (lepesTeszt(tid)) {
			ArrayList<Portal> portalok = (ArrayList<Portal>) hiv(tid, "epitPortal");
			// Az Unchecked cast warning nem lĂ©nyeges, az epitPortal biztosan
			// vagy ArrayList<Portal>-t vagy null-t ad vissza.
			if (portalok != null) {
				objektumok.put(pid1, portalok.get(0));
				objektumok.put(pid2, portalok.get(1));
			}
		}
	}

	/**
	 * FĂşrĂˇs tesztelĂ©se
	 * 
	 * @param id - ezen az azonosĂ­tĂłjĂş objektumon meghĂ­vĂłdik a fĂşrĂˇs
	 */
	public static void teszt_furas(String id) {
		if (lepesTeszt(id)) {
			hiv(id, "Furas");
		}
	}

	/**
	 * Napvihar okozĂˇsa aszteroidĂˇkon
	 * 
	 * @param aids - azoknak az aszteroidĂˇknak az azonosĂ­tĂłja vesszĹ‘kkel elvĂˇlasztva, amiken
	 *        napvihar lesz
	 */
	public static void teszt_napviharOkozasa(String aids) { // hehe
		if (aids.equals("null"))
			return;
		String[] aidsDarabolt = aids.split(",");
		for (int i = 0; i < aidsDarabolt.length; i++) {
			hiv(aidsDarabolt[i], "Napvihar");
		}
	}

	public static void teszt_randomValoszinuseg(String nev, String igazsagErtek) {
		Jatek.robot_robbanas_elso_szomszed = Boolean.parseBoolean(igazsagErtek);
	}
}

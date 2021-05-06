package src;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

public class Jatek {

	private static final Jatek INSTANCE = new Jatek();
	private static Map<String, Object> beallitasok_backup;

	public static Jatek getInstance() {
		return INSTANCE;
	}

	// PROGRAM BEÁLLÍTÁSOK
	public static Boolean COLOR_IN_TERMINAL = false;
	public static Boolean LOG_FUNCTION_CALLS = true;
	public static Boolean LOG_CONSTRUCTORS = true;
	public static Boolean LOG_GAME_INFO = true;
	// -1 = semmi | 0 = ctor és call, ha engedéyezve vannak, 1 = csak error | 2 =
	// 1+warn | 3 =
	// 2+info | 4 = 3+debug
	public static Integer LOG_LEVEL = 4;

	// JÁTÉK BEÁLLÍTÁSOK
	public static Integer MIN_SZEN = 3;
	public static Integer MIN_URAN = 3;
	public static Integer MIN_VIZJEG = 3;
	public static Integer MIN_VAS = 3;

	// a mozgás valószínűsége, ha nem mozog épp a robot akkor fúr
	public static Double ROBOT_MOZGAS_VALOSZINUSEG = 0.7;

	// ha ennél kevesebb telepes marad a játékban, a játéknak vége
	public static Integer MIN_TELEPES_NYERESHEZ = 2;

	public static Integer JATEKOS_SZAM = 2;
	public static Integer SZOMSZED_SZAM = 8;

	//
	public static Integer telepesszam = 0;
	public static Integer allapot = 0;
	private static Integer szamlalo = 0;
	private static FoFrame foFrame;
	public static Object lepesKesz = new Object();
	public static ArrayList<Leptetheto> leptethetok;
	private static NyersanyagKoltseg RobothozNyersanyag;
	private static NyersanyagKoltseg PortalhozNyersanyag;
	private static NyersanyagKoltseg UrbazishozNyersanyag;

	// lépés determinisztikussá tételéhez
	public static Boolean robot_robbanas_elso_szomszed = false;

	public static void beallitas_mentes() {
		beallitas_kezeles(true);
	}

	public static void beallitas_visszatoltes() {
		beallitas_kezeles(false);
	}

	/**
	 * Átadja azt a telepest a FoFrame-nek akinek most jön a köre.
	 * 
	 * @param t telepes akinek a köre jön
	 */
	public static void enKorom(Telepes t) {
		foFrame.setTelepes(t);
	}

	/**
	 * A kovetkező léptethető lépését hivja. Ha az utolsó léptethetőn vagyunk akkor vissz megy az
	 * első léptethetőre
	 */
	public static void kovetkezoLepes() {
		while (allapot == 0) {
			if (szamlalo == leptethetok.size())
				szamlalo = 0;
			try {
				new Thread(() -> {
					leptethetok.get(szamlalo++).Lepes();
				}).start();
			} catch (Exception e) {
				Log.error(e.toString());
			}
			synchronized (Jatek.lepesKesz) {
				try {
					lepesKesz.wait();
				} catch (Exception e) {
				}
			}
		}
		if (allapot == 1) {
			JOptionPane.showMessageDialog(foFrame, "Gratulálunk Nyertél", "Hurrá",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(foFrame, "Gratulálunk Vesztettél", "Jaj... ÚRISTEN!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		foFrame.dispose();
		// if (allapot == 0) {
		// if (szamlalo == leptethetok.size()) {
		// szamlalo = 0;
		// }
		// leptethetok.get(szamlalo++).Lepes();
		// } else {
		// if (allapot == 1) {
		// JOptionPane.showMessageDialog(foFrame, "Gratulálunk Nyertél", "Hurrá",
		// JOptionPane.INFORMATION_MESSAGE);
		// } else {
		// JOptionPane.showMessageDialog(foFrame, "Gratulálunk Vesztettél", "Jajne!",
		// JOptionPane.INFORMATION_MESSAGE);
		// }
		// foFrame.dispose();
		// }

	}

	/**
	 * Program indulásánál elmenti a játék beállításait és vissza is tölti azt
	 * 
	 * @param mentes ha igaz ment ha hamis visszatölt
	 */
	private static void beallitas_kezeles(Boolean mentes) {
		if (mentes) {
			if (beallitasok_backup != null)
				Log.warn("Alapértelmezések felülírva.");
			beallitasok_backup = new TreeMap<String, Object>();
		}
		Field[] fields = getInstance().getClass().getDeclaredFields();
		for (Field field : fields) {
			int modifier = field.getModifiers();
			if (Modifier.isFinal(modifier) || field.getName().equals("beallitasok_backup")
					|| field.getType().getName().equals("src.NyersanyagKoltseg"))
				continue;
			boolean private_field = Modifier.isPrivate(modifier);
			if (private_field) {
				field.setAccessible(true);
			}
			try {
				if (mentes)
					beallitasok_backup.put(field.getName(), field.get(getInstance()));
				else
					field.set(getInstance(), beallitasok_backup.get(field.getName()));
			} catch (Exception e) {
				Log.error(e.toString());
			} finally {
				if (private_field) {
					field.setAccessible(false);
				}
			}
		}
	}

	private Jatek() {
		leptethetok = new ArrayList<Leptetheto>();
	}

	/**
	 * Amikor létre jön a játék az építési nyersanyag költségek beállítódnak
	 * 
	 * 
	 */
	protected static void init() {
		Jatek.LOG_CONSTRUCTORS = false;
		Jatek.LOG_FUNCTION_CALLS = false;

		RobothozNyersanyag = new NyersanyagKoltseg();
		// RobothozNyersanyag.hozzaadNyersanyag(new Szen(false));
		RobothozNyersanyag.hozzaadNyersanyag(new Vas(false));
		// RobothozNyersanyag.hozzaadNyersanyag(new Uran(false));
		Telepes.hozzaadKoltseg(RobothozNyersanyag);

		PortalhozNyersanyag = new NyersanyagKoltseg();
		// PortalhozNyersanyag.hozzaadNyersanyag(new Uran(false));
		// PortalhozNyersanyag.hozzaadNyersanyag(new Vas(false));
		PortalhozNyersanyag.hozzaadNyersanyag(new Vas(false));
		// PortalhozNyersanyag.hozzaadNyersanyag(new Vizjeg(false));
		Telepes.hozzaadKoltseg(PortalhozNyersanyag);

		UrbazishozNyersanyag = new NyersanyagKoltseg();
		for (int i = 0; i < 3; i++) {
			UrbazishozNyersanyag.hozzaadNyersanyag(new Vas(false));
			// UrbazishozNyersanyag.hozzaadNyersanyag(new Szen(false));
			// UrbazishozNyersanyag.hozzaadNyersanyag(new Vizjeg(false));
			// UrbazishozNyersanyag.hozzaadNyersanyag(new Uran(false));
		}
		Aszteroida.hozzaadUrbazisKoltseg(UrbazishozNyersanyag);

		// foFrame = new FoFrame(new GombokPanel(), new RajzPanel(), new InfoPanel());
		Jatek.LOG_CONSTRUCTORS = true;
		Jatek.LOG_FUNCTION_CALLS = true;
	}

	public static void reset() {
		resetLepett();
		beallitas_visszatoltes();
		leptethetok.clear();
	}

	// Nem használjuk a tesztben, kezdetleges Kör
	public static void Kor() {
		Log.call();
		for (Leptetheto leptetheto : leptethetok) {
			leptetheto.Lepes();
		}
	}

	/**
	 * Törli a paraméterként kapott léptethetőt a listából
	 * 
	 * @param l a törlendő léptethető
	 */
	public static void torolLeptetheto(Leptetheto l) {
		Log.call();
		leptethetok.remove(l);
	}

	/**
	 * Csökkenti a telepesszémot eggyel, és ha már nincs elég akkor meghívja a játék vége vesztett
	 * fv.t
	 */
	public static void telepesMeghal() {
		Log.call();
		telepesszam--;
		if (telepesszam < MIN_TELEPES_NYERESHEZ) {
			jatekVegeVesztett();
		}
	}

	/**
	 * Gratulál a győzelemhez és megállítja a játékot.
	 */
	public static void jatekVegeNyert() {
		Log.call();
		Log.jatek("Gratulálunk nyertél!! :)");
		allapot = 1;
	}

	/**
	 * Vereségnél megállítja a játékot.
	 */
	public static void jatekVegeVesztett() {
		Log.call();
		Log.jatek("Gratulálunk vesztettél !! :)");
		allapot = -1;
	}

	public static void hozzaadLeptetheto(Leptetheto l) {
		Log.call();
		leptethetok.add(l);
	}

	/**
	 * Játékban szereplő játékosok, aszteroidák inicializálásáért felelős
	 * 
	 * @param nincsAllapot Ha igaz akkor létre hozza alap értelmezett helyzetet ha hamis akkor a
	 *        parancssorba lett beállítva valamilyen állapot
	 */
	public static void jatekInditas(boolean nincsAllapot) {
		Log.call();
		Integer log_bkp = LOG_LEVEL;
		LOG_LEVEL = -1;

		Nap n = (Nap) Szkeleton.getObj("nap");
		leptethetok.add(n);
		if (nincsAllapot) {
			ArrayList<Aszteroida> atmenetiAszteroidatar = new ArrayList<Aszteroida>();
			int vas = 0, szen = 0, vizjeg = 0, uran = 0;
			for (int i = 0; i < 50; i++) {
				Szkeleton.letrehoz("AszteroidaView", "AszteroidaView_" + i);
				Szkeleton.letrehoz("Aszteroida", "Aszteroida_" + i, "nap", "AszteroidaView_" + i);
				Nyersanyag ny;
				String nyersanyag_nev = "";
				switch (i % 5) { // 0 Vas 1 Szén 2 Vizjeg 3 Uran 4 üres
					case 0:
						nyersanyag_nev = "Vas_" + vas++;
						break;
					case 1:
						nyersanyag_nev = "Szen_" + szen++;
						break;
					case 2:
						nyersanyag_nev = "Vizjeg_" + vizjeg++;
						break;
					case 3:
						nyersanyag_nev = "Uran_" + uran++;
						break;
					default: // 4
						nyersanyag_nev = "";
						ny = null;
				}
				Aszteroida a = (Aszteroida) Szkeleton.getObj("Aszteroida_" + i);

				if (nyersanyag_nev != "") {
					Szkeleton.letrehoz(nyersanyag_nev.split("_")[0], nyersanyag_nev);
					ny = (Nyersanyag) Szkeleton.getObj(nyersanyag_nev);
					a.setNyersanyag(ny);
				}
				a.setNapkozel(RandomUtils.randomBooleanValoszinuseggel(0.1));
				a.setReteg(RandomUtils.randomIntHatarokKozott(1, 5));
				Szkeleton.hiv("AszteroidaView_" + i, "BeallitAszteroida", "Aszteroida_" + i); // lehetne
																								// getObj-al
																								// is...
				if (i == 0) {
					for (int j = 0; j < JATEKOS_SZAM; j++) {
						Szkeleton.letrehoz("Telepes", "Telepes_" + j);
						Telepes t = (Telepes) Szkeleton.getObj("Telepes_" + j);
						t.setSorszam(j);
						t.beallitAszteroida(a); // aszteroidanak is beallitja a szereplot
					}
					//Ufo hozzáadása
					Ufo ufo1 = new Ufo();
					Ufo ufo2 = new Ufo();
					Ufo ufo3 = new Ufo();
					ufo1.beallitAszteroida(a);
					ufo2.beallitAszteroida(a);
					ufo3.beallitAszteroida(a);
				}
				
				

				atmenetiAszteroidatar.add(a);
			}
			atmenetiAszteroidatar.get(0)
					.hozzaadSzomszed(atmenetiAszteroidatar.get(atmenetiAszteroidatar.size() - 1));
			for (int i = 0; i < atmenetiAszteroidatar.size() - 1; i++) {
				atmenetiAszteroidatar.get(i).hozzaadSzomszed(atmenetiAszteroidatar.get(i + 1));
				atmenetiAszteroidatar.get(i + 1).hozzaadSzomszed(atmenetiAszteroidatar.get(i));
				for (int j = 0; j < SZOMSZED_SZAM - 2; j++) {
					Aszteroida randomAszteroida = atmenetiAszteroidatar.get(RandomUtils
							.randomIntHatarokKozott(0, atmenetiAszteroidatar.size() - 1));
					if (atmenetiAszteroidatar.get(i) != randomAszteroida && !atmenetiAszteroidatar
							.get(i).getSzomszedok().contains(randomAszteroida)) {
						atmenetiAszteroidatar.get(i).hozzaadSzomszed(randomAszteroida);
						randomAszteroida.hozzaadSzomszed(atmenetiAszteroidatar.get(i));
					}

				}
			}
		}

		allapot = 0; // futó állapot
		LOG_LEVEL = log_bkp;
		InfoPanel infoPanel = new InfoPanel();
		infoPanel.setTelepes((Telepes) leptethetok.get(1));
		RajzPanel rajzPanel = new RajzPanel();
		// rajzPanel.setAszteroida(new Aszteroida(n,)); //TODO ez nem fasza igy teljesen
		GombokPanel gombokPanel = new GombokPanel();
		gombokPanel.setTelepes((Telepes) leptethetok.get(1));
		foFrame = new FoFrame(gombokPanel, rajzPanel, infoPanel);
		foFrame.setTelepes((Telepes) leptethetok.get(1));
		foFrame.setVisible(true);
		kovetkezoLepes();
	}

	/**
	 * Játék adatait írja ki
	 * 
	 * @return játék adatai
	 */
	public String toString() {
		return telepesszam.toString() + ":" + leptethetok.size();

	}

	/**
	 * Állapotot adja vissza
	 * 
	 * 
	 */
	public Integer getAllapot() {
		return allapot;
	}

	/**
	 * Lellenőrzi mindenki lépett e
	 * 
	 * @return mindenki lépett-e
	 */
	public static Boolean mindenkiLepett() {
		for (Leptetheto l : leptethetok) {
			if (l.lepette() == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Mindenki lépett állapotát hamissá teszi
	 * 
	 * 
	 */

	public static void resetLepett() {
		for (Leptetheto l : leptethetok) {
			l.resetLepett();
		}
	}

	public FoFrame getFoFrame() {
		return foFrame;
	}

}

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
	// TODO ez m�r nem csak szkeleton, most m�r ez a "j�t�k"

	public static final Szkeleton INSTANCE = new Szkeleton();

	private static Map<String, Object> objektumok;

	private Szkeleton() {
		objektumok = new TreeMap<>();
		objektumok.put("jatek", Jatek.INSTANCE);
		objektumok.put("nap", new Nap());
		// TODO itt bele kell rakni a j�t�k automatikusan l�trehozott glob�lis objektumait a t�mbbe.
	}

	private static Object egyParameterTipusForditas(Class<?> tipus, String argumentum)
			throws Exception {
		// van ilyen objektumunk t�rolva v�letlen?
		if (objektumok.containsKey(argumentum.toLowerCase())) {
			if (tipus.isInstance(objektumok.get(argumentum))) {
				// �nnepelj�nk, megtal�ltuk
				return objektumok.get(argumentum);
			}
		}
		// fallback
		if (argumentum.equals("null")) {
			return null;
		} else {
			try {
				return tipus.getMethod("valueOf", String.class).invoke(null, argumentum);
			} catch (Exception e) {
				Log.error(e.toString());
				Log.debug(tipus.toString() + " " + argumentum.toString());
				throw new Exception("Ford�t�s sikertelen", e);
			}
		}
	}

	private static Object[] tobbParameterTipusForditas(Class<?>[] tipusok, String[] argumentumok) {
		if (tipusok.length == argumentumok.length) {
			ArrayList<Object> parameterek = new ArrayList<>();
			for (int i = 0; i < tipusok.length; i++) {
				try {
					parameterek.add(egyParameterTipusForditas(tipusok[i], argumentumok[i]));
				} catch (Exception e) {
					Log.error(e.toString());
					return null;
				}
			}
			return parameterek.toArray();
		}
		return null;
	}

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

	public static Object hiv(String id, String fuggveny_nev, String... argumentumok) {
		if (!objektumok.containsKey(id)) {
			Log.error("A megadott azonos�t� nem l�tezik! (" + id + ")");
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

	public static void Menu() {

		switch (Cin.kerdez_tobbvalasz("MEN�", "Mozg�s �rhaj�val", "Mozg�s teleport kapun kereszt�l",
				"B�ny�szat", "V�zj�g F�r�s", "Ur�n f�r�s", "F�r�s vas", "Port�lkapu �p�t�s",
				"Robot �p�t�s", "Nyersanyag visszahelyez�s", "Port�l lehelyez�s",
				"Robot Ur�n f�r�s", "Napvihar")) {
			case 1:
				MozgasUrhajoval();
				break;
			case 2:
				mozgasTeleporttal();
				break;
			case 3:
				BanyaszatMenu();
				break;
			case 4:
				Vizjegfuras();
				break;
			case 5:
				telepesFurasUran();
				break;
			case 6:
				Vasfuras();
				break;
			case 7:
				portalkapuEpites();
				break;
			case 8:
				robotEpites();
				break;
			case 9:
				NyersanyagVisszahelyezesMenu();
				break;
			case 10:
				portalLehelyezes();
				break;
			case 11:
				robotFurasUran();
				break;
			case 12:
				Napvihar();
				break;
			case 13:
				System.exit(0);
				break;

			default:
				break;
		}


	}

	public static void BanyaszatMenu() {
		switch (Cin.kerdez_tobbvalasz("B�NY�SZAT", "Ur�n B�ny�szat", "V�zj�g B�ny�szat",
				"Sz�n B�ny�szat", "Vas B�ny�szat")) {
			case 1:
				uranBanyaszat();
				break;
			case 2:
				vizjegBanyaszat();
				break;
			case 3:
				szenBanyaszat();
				break;
			case 4:
				vasBanyaszat();
				break;

			default:
				break;
		}
	}

	public static void NyersanyagVisszahelyezesMenu() {
		System.out.println("1. Ur�n visszahelyez�s\r\n" + "2. V�zj�g visszahelyez�s\r\n"
				+ "3. Sz�n visszahelyez�s\r\n" + "4. Vas visszahelyez�s");
		switch (Cin.kerdez_tobbvalasz("B�NY�SZAT", "Ur�n visszahelyez�s", "V�zj�g visszahelyez�s",
				"Sz�n visszahelyez�s", "Vas visszahelyez�s")) {
			case 1:
				UranVisszahelyezes();
				break;
			case 2:
				VizjegVisszahelyezes();
				break;
			case 3:
				VasVisszahelyez();
				break;
			case 4:
				SzenVisszahelyez();
				break;

			default:
				break;
		}
	}


	// Az elej�n lev� inicializ�l�s mindenhol a megfelel� m�k�d�s �rdek�ben van.
	public static void MozgasUrhajoval() {
		Telepes t = new Telepes();
		Aszteroida regi = new Aszteroida(1, true, new Nap(), new Uran());
		Aszteroida uj = new Aszteroida(1, true, new Nap(), new Uran());
		regi.hozzaadSzomszed(uj);
		uj.hozzaadSzomszed(regi);
		regi.hozzaadSzereplo(t);
		t.beallitAszteroida(regi);
		t.Mozgas(0);
	}

	public static void VasVisszahelyez() {
		Aszteroida a = new Aszteroida(1, true, new Nap(), null);
		Vas v = new Vas();
		Telepes t = new Telepes();
		a.hozzaadSzereplo(t);
		t.beallitAszteroida(a);
		t.hozzaadNyersanyag(v);
		t.visszarakNyersanyag(v);
	}

	public static void SzenVisszahelyez() {
		Aszteroida a = new Aszteroida(1, true, new Nap(), null);
		Szen sz = new Szen();
		Telepes t = new Telepes();
		a.hozzaadSzereplo(t);
		t.beallitAszteroida(a);
		t.hozzaadNyersanyag(sz);
		t.visszarakNyersanyag(sz);
	}

	public static void UranVisszahelyezes() {
		Aszteroida a = new Aszteroida(1, true, new Nap(), null);
		Aszteroida b = new Aszteroida(1, true, new Nap(), new Vas());
		Uran u = new Uran();
		Telepes t = new Telepes();
		Telepes t2 = new Telepes();
		t2.setPortal(new Portal());
		t.hozzaadNyersanyag(new Szen());
		t.setPortal(new Portal());
		t2.hozzaadNyersanyag(new Szen());
		b.hozzaadSzereplo(t2);
		a.hozzaadSzereplo(t);
		a.hozzaadSzomszed(b);
		b.hozzaadSzomszed(a);
		t.beallitAszteroida(a);
		t2.beallitAszteroida(b);
		t.hozzaadNyersanyag(u);
		t.visszarakNyersanyag(u);
	}

	public static void VizjegVisszahelyezes() {
		Aszteroida a = new Aszteroida(1, true, new Nap(), null);
		Vizjeg v = new Vizjeg();
		Telepes t = new Telepes();
		a.hozzaadSzereplo(t);
		t.beallitAszteroida(a);
		t.hozzaadNyersanyag(v);
		t.visszarakNyersanyag(v);
	}

	public static void mozgasTeleporttal() {
		Nap nap = new Nap();
		Aszteroida a = new Aszteroida(3, false, nap, new Szen());
		Portal p2 = new Portal();
		Aszteroida b = new Aszteroida(3, false, nap, new Szen());
		Portal p = new Portal();
		Telepes t = new Telepes();
		p.setBirtokos(t);
		p2.setBirtokos(t);
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		p.beallitPar(p2);
		p2.beallitPar(p);
		p.setVegpont(a);
		p2.setVegpont(b);
		a.hozzaadSzomszed(p);
		t.Mozgas(0); // Ha nem mozgunk akkor is megk�rdezi megvan-e az �sszes nyersanyag.
						// Rendes j�t�kban ez egy automatikus ellen�rz�s lesz ami nem ront el semmit
						// �s nem fogyaszt sok er�forr�st �gy fejleszt�i d�nt�s miatt marad.
	}

	public static void uranBanyaszat() {
		Nap nap = new Nap();
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3, false, nap, new Uran());
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		a.Banyaszat();
	}

	public static void vasBanyaszat() {
		Nap nap = new Nap();
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3, false, nap, new Vas());
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		a.Banyaszat();
	}

	public static void vizjegBanyaszat() {
		Nap nap = new Nap();
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3, false, nap, new Vizjeg());
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		a.Banyaszat();
	}

	public static void szenBanyaszat() {
		Nap nap = new Nap();
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3, false, nap, new Szen());
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		a.Banyaszat();
	}

	public static void robotFurasUran() {
		Robot r = new Robot();
		Robot r2 = new Robot();
		Aszteroida a = new Aszteroida(2, false, new Nap(), new Uran());
		Aszteroida b = new Aszteroida(2, false, new Nap(), new Vas());

		b.hozzaadSzereplo(r2);
		a.hozzaadSzereplo(r);
		a.hozzaadSzomszed(b);
		b.hozzaadSzomszed(a);
		r.beallitAszteroida(a);
		r2.beallitAszteroida(b);
		a.Furas();

	}

	public static void telepesFurasUran() {

		Aszteroida a = new Aszteroida(1, true, new Nap(), new Uran());
		Aszteroida b = new Aszteroida(1, true, new Nap(), new Szen());
		Telepes t = new Telepes();
		Telepes t2 = new Telepes();
		t2.setPortal(new Portal());
		t.hozzaadNyersanyag(new Szen());
		t.setPortal(new Portal());
		t2.hozzaadNyersanyag(new Szen());
		b.hozzaadSzereplo(t2);
		a.hozzaadSzereplo(t);
		a.hozzaadSzomszed(b);
		b.hozzaadSzomszed(a);
		t.beallitAszteroida(a);
		t2.beallitAszteroida(b);

		a.Furas();
	}

	public static void Vizjegfuras() {
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(1, true, new Nap(), new Vizjeg());
		t.beallitAszteroida(a);
		t.Furas();
	}

	public static void Vasfuras() {
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(1, true, new Nap(), new Vas());
		t.beallitAszteroida(a);
		t.Furas();
	}

	public static void Napvihar() {
		Nap nap = new Nap();
		Aszteroida a = new Aszteroida(1, true, nap, new Vas());
		Telepes t = new Telepes();
		Robot r = new Robot();
		t.hozzaadNyersanyag(new Vas());
		t.setPortal(new Portal());
		t.beallitAszteroida(a);
		r.beallitAszteroida(a);
		a.hozzaadSzereplo(r);
		a.hozzaadSzereplo(t);
		nap.hozzaadAszteroidak(new ArrayList<Aszteroida>(Arrays.asList(a)));
		nap.Lepes();
	}

	public static void portalkapuEpites() {
		Telepes t = new Telepes();
		NyersanyagKoltseg nyk1 = new NyersanyagKoltseg();
		NyersanyagKoltseg nyk2 = new NyersanyagKoltseg(); // robot�pt�shez haszn�lt
		t.hozzaadKoltseg(nyk1);
		t.hozzaadKoltseg(nyk2);

		t.epitPortal();
	}

	public static void robotEpites() {
		Telepes t = new Telepes();
		NyersanyagKoltseg nyk1 = new NyersanyagKoltseg(); // portal�p�t�shez haszn�lt
		NyersanyagKoltseg nyk2 = new NyersanyagKoltseg();
		t.hozzaadKoltseg(nyk1);
		t.hozzaadKoltseg(nyk2);
		t.beallitAszteroida(new Aszteroida(0, false, null, null));

		t.epitRobot();

	}

	public static void portalLehelyezes() {
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3, false, new Nap(), new Szen()); // telepes helyezkedik el
																		// rajta
		Aszteroida a2 = new Aszteroida(3, false, new Nap(), new Szen()); // p2 lesz rajta
		t.beallitAszteroida(a);

		Portal p1 = new Portal(); // telepes�
		Portal p2 = new Portal(); // p1 szomsz�dja

		p1.setBirtokos(t);
		p2.setBirtokos(t);
		if (Cin.getBool("Van a telepesn�l portal?")) {
			t.setPortal(p1);

			p1.beallitPar(p2);
			p2.beallitPar(p1);

			p2.setVegpont(a2);

			t.lehelyezPortal(p1);
		}
	}
}

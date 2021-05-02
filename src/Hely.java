package src;

/**
 * Az utazás az ezt az osztályt megvalósítókon keresztül létrejöhet
 * @author ppeka
 *
 */
public abstract class Hely {
	/**
	 * Robbanás, amit a leszármazottak megvalósítanak
	 */
	protected JatekView jatekView;
	public abstract void Robbanas();

	/**
	 * Szereplő utazása, amelyet a leszármazottak megvalósítanak
	 * @param sz - szereplő, aki utazik
	 */
	public abstract void Utazas(Szereplo sz);

	/**
	 * Ha nem valósítják meg a leszármazottak, akkor robban
	 * @param a
	 */
	public void szomszedRobbant(Aszteroida a) { 
		Robbanas(); 
	}

	/**
	 * portál utazása, amelyet a leszármazottak megvalósítanak
	 * @param hely - portal, ami utazik
	 */
	public abstract void utazasHely(Portal hely);

	/**
	 * Leszármazottak megvalósíthatják, alapból nem csinál semmit
	 */
	public void szomszedNapvihar() {

	}
	
	abstract public Aszteroida szomszedosAszteroida();
	
	public JatekView getView() {
		return jatekView;
	}
}

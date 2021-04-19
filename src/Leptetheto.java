package src;

// Azok az osztályok valósítják meg, amelyek minden körben lépnek
public interface Leptetheto {
  public void Lepes(); // mi történjen egy lépésben

  /**
   * Visszaadja, hogy lépett-e már
   * @return Ha igen true, ha nem false 
   */
  public Boolean lepette();

  /**
   * Reseteli a lépését
   */
  public void resetLepett();
}

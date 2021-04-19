package src;

// Azok az oszt�lyok val�s�tj�k meg, amelyek minden k�rben l�pnek
public interface Leptetheto {
  public void Lepes(); // mi t�rt�njen egy l�p�sben

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

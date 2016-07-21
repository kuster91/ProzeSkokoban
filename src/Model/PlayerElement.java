package Model;

/**
 * klasa zawierajaca model graficzny gracza
 */
public class PlayerElement extends AbstractElement {

	/**
	 * Zmienna przechowujaca sciezke do grafiki skrzynki
	 */
	private final static String SOURCE = "pic\\player.gif";

	/**
	 * Konstruktor
	 * 
	 * @param x
	 *            wspolzedna wyswietlenia grafiki
	 * @param y
	 *            wspolzedna wyswietlenia grafiki
	 */
	public PlayerElement(int x, int y) {
		/**
		 * konstruktor odwolujacy sie do nadklasy AbstractElement ustawia
		 * wspolzedne X i Y
		 */
		super(x, y, SOURCE);
	}

	/**
	 * Przeciazenie metody equals
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PlayerElement))
			return false;
		PlayerElement s = (PlayerElement) obj;
		if (this.getX() == s.getX() && this.getY() == s.getY())
			return true;
		return false;
	}
}

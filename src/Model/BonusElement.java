package Model;

/**
 * klasa zawierajaca model bonusowego elementu gry *
 */
public class BonusElement extends AbstractElement {
	/**
	 * Zmienna przechowujaca sciezke do grafiki skrzynki
	 */
	private final static String SOURCE = "pic\\sphere.gif";

	/**
	 * Konstruktor odwolujacy sie do nadklasy AbstractElement
	 * 
	 * @param x
	 *            wspolzedna wyswietlenia grafiki
	 * @param y
	 *            wspolzedna wyswietlenia grafiki
	 */
	public BonusElement(int x, int y) {
		super(x, y, SOURCE);
	}

	/**
	 * Przeciazenie metody equals
	 */
	public boolean equals(Object obj) // Kacper nie wiem czy to Ci sie przyda,
										// ja tego nie wykorzystuje poki co
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ChestElement))
			return false;
		ChestElement s = (ChestElement) obj;
		if (this.getX() == s.getX() && this.getY() == s.getY())
			return true;
		return false;
	}

	/**
	 *
	 * @param obj
	 *            obiekt ktory porównujemy
	 * @return wzraca czy dane obiekty maja maja te same wspolrzedne
	 */
	public boolean equalsXY(PlayerElement obj) {
		if (this.getX() == obj.getX() && this.getY() == obj.getY())
			return true;
		else
			return false;
	}

}
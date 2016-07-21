package Model;

/**
 * Klasa przedstawiajaca model pojedycznego obiektu sciany
 */
public class WallElement extends AbstractElement {

	/**
	 * Zmienna przechowujaca sciezke do grafiki skrzynki
	 */
	private final static String SOURCE = "pic\\wall.png";
	/**
	 * MARK
	 *            zmienna odpowiedzialna za symbol reprezentujacy element w
	 *            plikach opisujacych dany poziom
	 */
	private final static String MARK = "#";

	/**
	 * Konstrutor
	 * 
	 * @param x
	 *            wspolzedna wyswietlenia grafiki
	 * @param y
	 *            wspolzedna wyswietlenia grafiki
	 */
	public WallElement(int x, int y) {
		/**
		 * konstruktor odwolujacy sie do nadklasy AbstractElement ustawia
		 * wspolzedne X i Y oraz przypisuje źródło klasie IMAGE
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
		if (!(obj instanceof WallElement))
			return false;
		WallElement s = (WallElement) obj;
		if (this.getX() == s.getX() && this.getY() == s.getY())
			return true;
		return false;
	}

}

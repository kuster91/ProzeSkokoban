package Model;

/**
 * Klasa przedstawiajaca model skrzyni
 */
public class ChestElement extends AbstractElement {
	/**
	 * SOURCE- Zmienna przechowujaca sciezke do grafiki skrzynki
	 */
	private final static String SOURCE = "pic\\chest.gif";
	/**
	 * MARK - zmienna odpowiedzialna za symbol reprezentujacy element w plikach
	 * opisujacych dany poziom
	 */
	private final static String MARK = "$";
	/**
	 * zmienna odpowiedzialna za przesuniecie elementu w poziomie
	 */
	private int moveX = 0;
	/**
	 * zmienna odpowiedzialna za przesuniecie elementu w pionie
	 */
	private int moveY = 0;

	/**
	 * Konstruktor
	 * 
	 * @param x
	 *            wspolrzedna wyswietlenia grafiki
	 * @param y
	 *            wspolrzedna wyswietlenia grafiki
	 */

	public ChestElement(int x, int y) {
		/**
		 * konstruktor odwolujacy sie do nadklasy AbstractElement ustawia
		 * wspolzedne X i Y oraz przypisuje źródło klasie IMAGE
		 */
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
	public boolean equalsXY(GoalElement obj) {
		if (this.getX() == obj.getX() && this.getY() == obj.getY())
			return true;
		else
			return false;
	}

	/**
	 * metoda ustawiajaca przesuniecie elementu o
	 * 
	 * @param moveX
	 *            w poziomie
	 */
	public void setMoveX(int moveX) {
		this.moveX = moveX;
	}

	/**
	 * metoda ustawiajaca przesuniecie elementu o
	 * 
	 * @param moveY
	 *            w pionie
	 */
	public void setMoveY(int moveY) {
		this.moveY = moveY;
	}

	/**
	 * metoda
	 * 
	 * @return przesuniecie w poziomie
	 */
	public int getMoveX() {
		return moveX;
	}

	/**
	 * metoda
	 * 
	 * @return przesuniecie w pionie
	 */
	public int getMoveY() {
		return moveY;
	}
}

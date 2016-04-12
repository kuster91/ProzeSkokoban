package Model;

import java.awt.Graphics;
/**
 * klasa zawierajaca model graficzny gracza
 *
 */
public class PlayerElement extends AbstractElement {

	private final static String SOURCE = "pic\\player.png";
/**
 *  Konstruktor
 * @param x ustala wspol. X na planszy
 * @param y ustala wspol. Y na planszy
 */
	public PlayerElement(int x, int y) {
		super(x, y, SOURCE);
		// TODO Auto-generated constructor stub
	}
}

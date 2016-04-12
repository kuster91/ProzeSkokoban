package Model;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Klasa przedstawiajaca model pojedycznego obiektu sciany
 */
public class WallElement extends AbstractElement {

	private final static String SOURCE = "pic\\wall.png";

	public WallElement(int x, int y) {
		super(x, y, SOURCE);
	}
}

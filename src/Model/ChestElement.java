package Model;

import java.awt.Graphics;

/**
 * Klasa przedstawiajaca model skrzyni
 *
 */
public class ChestElement extends AbstractElement {

	private final static String SOURCE = "pic\\chest.png";

	public ChestElement(int x, int y) {
		super(x, y, SOURCE);
	}

}

package Model;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Model podlogi po ktorej bedize sie poruszal gracz
 *
 */
public class FloorElement extends AbstractElement {

	private final static String SOURCE = "pic\\floor.png";

	public FloorElement(int x, int y) {
		super(x, y, SOURCE);
		}
}

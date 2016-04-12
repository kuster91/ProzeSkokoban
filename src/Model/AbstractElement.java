package Model;

import java.awt.*;
import javax.swing.*;

/**
 * Klasa przedstawiajaca Abstrakcyjny model elementu z ktorego beda skladac sie
 * plansze poziomow
 *
 */
public class AbstractElement extends JComponent {
	private int x;
	private int y;
	private Image image;

	/**
	 * Konstuktor
	 * @param x wspolrzedna pozioma elementu
	 * @param y wspolrzedna pionowa elementu
	 */
	public AbstractElement(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 *	Konstruktor
	 * @param x wspolrzedna pozioma elementu
	 * @param y wspolrzedna pionowa elementu
	 * @param source scie¿ka dostepu do pliku z grafika elementu
	 */
	public AbstractElement(int x, int y, String source) {
		this.x = x;
		this.y = y;
		this.image = new ImageIcon(source).getImage();
	}

	/**
	 * @return zwraca wspolrzedna X elementu
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return zwraca wspolrzedna Y elementu
	 */
	public int getY() {
		return y;
	}
	/**
	 *
	 * @return zwraca obrazek elementu
	 */
	public Image getImage() {

		return image;
	}

	/**
	 * metoda rysujaca element
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(image, x, y, null);
	}
	/**
	 * metoda przesuwajaca element
	 */
	public void move() {

	}
}

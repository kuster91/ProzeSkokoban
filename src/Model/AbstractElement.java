package Model;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Klasa przedstawiajaca Abstrakcyjny model elementu z ktorego beda skladac sie
 * plansze poziomow
 */
public class AbstractElement extends JComponent {
	/**
	 * Parametry przechwujace wspolrzedne do wyswietlania elementu na
	 * ekranie @param x - pozioma;
	 */
	private int x;
	/**
	 * Parametry przechwujace wspolrzedne do wyswietlania elementu na
	 * ekranie @param y - pionowa
	 */
	private int y;
	/**
	 * Klasa Image przechowująca grafike do wyswietlania na ekranie
	 */
	private Image image;

	/**
	 * Konstuktor
	 *
	 * @param x
	 *            wspolrzedna pozioma elementu
	 * @param y
	 *            wspolrzedna pionowa elementu
	 */
	public AbstractElement(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Konstruktor
	 *
	 * @param x
	 *            wspolrzedna pozioma elementu
	 * @param y
	 *            wspolrzedna pionowa elementu
	 * @param source
	 *            scie�ka dostepu do pliku z grafika elementu
	 */
	public AbstractElement(int x, int y, String source) {
		this.x = x;
		this.y = y;
		this.image = new ImageIcon(source).getImage();
	}

	/**
	 * @param x jako wspolrzedna X elementu
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * ustawia
	 * 
	 * @param y
	 *            jako wspolrzedna Y elementu
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Ustawia obie wspolrzedne elementu na raz
	 * 
	 * @param x
	 *            wspolrzedna X
	 * @param y
	 *            wspolrzedna Y
	 */
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	} // jestem leniwy i robie to w 1 funkcji

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
}
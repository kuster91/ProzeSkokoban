package View;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import Model.*;

/**
 * klasa zawierajaca panel w ktorym toczyc sie bedzie rozgrywka
 *
 */
public class GameAreaPanel extends JPanel {

	private ArrayList<AbstractElement> map = new ArrayList();
	private PlayerElement player;
	private int mapWidth;
	private int mapHeight;
	private int spaceX = 50;
	private int spaceY = 50;
	private boolean completed = false;

	/**
	 * Konstruktor
	 * @param fileName sciezka dostepu do pliku z poziomem gry
	 */
	public GameAreaPanel(String fileName) {
		LevelLoader level = new LevelLoader(fileName);
		map.addAll(level.getMap());
		mapHeight = level.getMapHeight();
		mapWidth = level.getMapWidth();
		this.setLayout(null);
	}
/**
 * metoda rysujaca poziom
 */
	public void paint(Graphics g) {
		for (int i = 0; i < map.size(); i++) {
			AbstractElement item = (AbstractElement) map.get(i);
			g.drawImage(item.getImage(), item.getX() * spaceX, item.getY() * spaceY, spaceX, spaceY, this);
		}
	}

}

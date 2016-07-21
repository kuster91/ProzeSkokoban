package View;

import Model.LevelLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class ImagePanel extends JPanel implements ComponentListener, WindowStateListener {
	/**
	 * Zmienna przechowujaca nasze tlo(obraz)
	 */
	private Image img;
	/**
	 * Zmienna sklaujac teksture obrazka w poziomie
	 */
	private int spaceX;
	/**
	 * Zmienna skalujaca teksture obrazka w pionie
	 */
	private int spaceY;
	/**
	 * Jest to JPanel na ktorym bedziemy wywolywac malowanie, lisnery
	 */
	private MainFrame frame;
	/**
	 * Zmienna przechowujaca aktualnie posiadane zycia
	 */
	private String liczbaZyc;
	/**
	 * Zmienna przechowujaca odczytany czas na mape
	 */
	private double czasGry;
	/**
	 * Zmienna przechowujaca pozostaly czas na wykonanie celow mapy
	 */
	private String runingTime;
	/**
	 * Zmienna przechowujaca warunek skonczenia planszy
	 */
	private boolean completed = false;
	/**
	 *  score-
	 *            wynik punktowy
	 */
	private int score;
	/**
	 * area-
	 *            plansza gry
	 */
	private GameAreaPanel area;
	/**
	 * imPanel-
	 *            tlo
	 */
	private ImagePanel imPanel;
	/**
	 *  TimeSpawn-
	 *            odstep czasowy
	 */
	private ArrayList<Integer> TimeSpawn;
	/**
	 *  bonusPoints-
	 *            punkty bonusowe
	 */
	private int bonusPoints = 0;

	/**
	 * konstrukor klasy
	 * 
	 * @param img zrodlo obrazka
	 * @param frame  ramka 
	 * @param DX wspolrzedna pozioma 
	 * @param DY wspolrzedna pionowa 
	 * @param liczbaZyc liczba zyc 
	 * @param score  wynik 
	 * @param przelicznikCzasu przelicznik czasu 
	 * @param tmp tymczasowy string
	 * @param area plansza gry
	 */
	public ImagePanel(Image img, MainFrame frame, int DX, int DY, String tmp, int liczbaZyc, int score,
			int przelicznikCzasu, GameAreaPanel area) {
		this.area = area;
		TimeSpawn = area.getTimeSpawn();
		this.score = score;
		this.liczbaZyc = Integer.toString(liczbaZyc);
		LevelLoader level;
		level = new LevelLoader(tmp);
		if (frame.isStatus())
			czasGry = (double) level.getTime();
		else
			czasGry = level.getTime() * przelicznikCzasu;
		spaceX = DX;
		spaceY = DY;
		this.frame = frame;
		this.img = img;
		setLayout(null);
		addComponentListener(this);
		frame.addWindowStateListener(this);
		runingTime = Double.toString(czasGry) + 1;
		wyswietlanieCzasu(); // wyswietla czas gry
		this.setPreferredSize(new Dimension(DX, DY)); // ustalanie wielkosc
		frame.getContentPane().add(this, BorderLayout.NORTH); // ustalanie
																// pozycji
		imPanel = this;
	}

	/**
	 * Watek ktory odlicza czas i go wyswietla
	 */
	private void wyswietlanieCzasu() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						if (completed) {
							Thread.currentThread().interrupt();
						}
						if (czasGry < 0) {
							runingTime = "Out Of Life!";
							completed = true;
						} else {
							runingTime = "Time left: " + String.format("%.2f", czasGry);
						}
						frame.repaint();
						frame.revalidate();
						for (int i = 0; i < TimeSpawn.size(); i++) {
							if ((int) czasGry == TimeSpawn.get(i)) {
								area.spawnBonus(imPanel);
								TimeSpawn.remove(i);
							}
						}
						Thread.sleep(20);


					}
				} catch (InterruptedException exp) {
					Thread.currentThread().interrupt();
				}
			}
		});
		t.start();
	}

	/**
	 * metoda rysujaca komponent
	 */
	public void paintComponent(Graphics g) {

		g.drawImage(img, 0, 0, spaceX, spaceY, this);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.setColor(Color.GREEN);
		g.drawString(liczbaZyc, 20, 40);
		g.drawString(runingTime, 100, 40);
		g.drawString(Integer.toString(score), 350, 40);
	}

	/**
	 *
	 * Event skalujacy panel wyswietlajacy informacje
	 */
	public void componentResized(ComponentEvent e) {
		Rectangle r = getBounds();
		spaceX = r.width;
	}

	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentShown(ComponentEvent e) {
	}

	/**
	 * Przeciazenie metody z WindowStateListner Wylicza nowe przeliczniki
	 * tekstur podczas maxymalizowania i minimalizowania okna
	 */
	public void windowStateChanged(WindowEvent evt) {
		int oldState = evt.getOldState();
		int newState = evt.getNewState();

		if ((oldState & Frame.MAXIMIZED_BOTH) == 0 && (newState & Frame.MAXIMIZED_BOTH) != 0) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			spaceX = (int) screenSize.getWidth();

		} else if ((oldState & Frame.MAXIMIZED_BOTH) != 0 && (newState & Frame.MAXIMIZED_BOTH) == 0) {
			Rectangle r = frame.getBounds();
			spaceX = r.width;

		}

	}

	/**
	 * metoda ustalajaca status ukonczenia mapy
	 */
	public void setCompleted() {
		completed = true;
	}

	/**
	 *
	 * @return zwraca status ukonczenia mapy
	 */
	public boolean getCompleted() {
		return completed;
	}

	/**
	 * 
	 * @return czas gry
	 */
	public double getTimeLeft() {
		return czasGry;
	}

	/**
	 * dodaje 500punktow bonusowych
	 */
	public void setBonusPoints() {
		bonusPoints += 500;
	}

	/**
	 * 
	 * @return liczbe bounsowych punktow
	 */
	public int getBonusPoints() {
		return bonusPoints;
	}
	/**
	 * Metoda ustawiajaca czas gry
	 * @param czas czas pozostaly na dana mape
	 */
	public void setTimeLeft(double czas)
	{
		czasGry=czas;
	}
	}

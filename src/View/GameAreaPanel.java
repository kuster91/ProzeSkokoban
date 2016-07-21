package View;

import Model.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

/**
 * klasa zawierajaca panel w ktorym toczyc sie bedzie rozgrywka
 *
 */
public class GameAreaPanel extends JPanel implements ComponentListener, WindowStateListener, KeyListener {
	/**
	 * lista przechowujaca elemnty do wyswietlenia na mapie; map - cala mapa;
	 * chestrs - skrzynie; goals - miejsca w ktorzych nalezy umiescic skrzynie;
	 * walls - sciany;
	 */
	private ArrayList<AbstractElement> map = new ArrayList();
	private ArrayList<ChestElement> chests = new ArrayList<>();
	private ArrayList<GoalElement> goals = new ArrayList<>();
	private ArrayList<WallElement> walls = new ArrayList<>();
	/**
	 * zmienne przechowujace ilosc kolumn mapy
	 */
	private int mapWidth;
	/**
	 * Zmienna przechowujac ilosc wierszy mapy
	 */
	private int mapHeight;
	/**
	 * Zmienna skalujaca tekstury w poziomoe
	 */
	private double spaceX;
	/**
	 * Zmienna skalujaca tekstury w pionie
	 */
	private double spaceY;
	/**
	 * parametr przechowujacy chec zmiany polozenie gracz - w poziomie
	 */
	private int dx;
	/**
	 * parametr przechowujacy chec zmiany polozenie gracz - w pionie
	 */
	private int dy;
	/**
	 * Zmienna informujaca o ukonczeniu poziomu
	 */
	private boolean completed = false;
	/**
	 * przekazane okno gry, sluzy do ustalenia rozmiaru aktualnego okna +
	 * oblsuga Eventu maxymalizowani i minimalizowaniu okna
	 */
	private MainFrame frame;
	/**
	 * Zmienna przechowujaca informacje o graczu
	 */
	private PlayerElement player;
	/**
	 * Flaga potrzebna do animacji ruchu
	 */
	private boolean canMove = true;
	/**
	 * przesuniecie w poziomie
	 */
	private int moveX = 0;
	/**
	 * 
	 * przesuniecie w pionie
	 */
	private int moveY = 0;
	/**
	 * 
	 * maxymalny bonus
	 */
	private int bonusMax = 0;
	/**
	 * 
	 * minimalny bonus
	 */
	private int bonusMin = 0;
	/**
	 * 
	 * time - czas gry
	 */
	private int time;

	private ArrayList<Integer> timeSpawn = new ArrayList<>();
	/**
	 * floors- lista z elementami podlogi
	 */
	private ArrayList<FloorElement> floors = new ArrayList<>();
	/**
	 * bonno- przechowuje element bonusu
	 */
	private BonusElement bonno = null;
	/**
	 * bonno- flaga mowia o tym czy jest bonus
	 */
	private boolean flagBonus = false;
	/**
	 * tymczasowy stan gry
	 */
	private ImagePanel tmpState;

	/**
	 * Konstruktor
	 *
	 * @param fileName sciezka dostepu do pliku z poziomem gry 
	 * @param frame przekazuje okna aby mozna bylo obsluzyc event oraz do obliczen zbalansowania tekstur
	 * @param width szerokosc
	 * @param height wysokosc
	 */
	public GameAreaPanel(String fileName, MainFrame frame, int width, int height) {
		this.frame = frame;
		Settings tmpSett = new Settings("Ustawienia.txt");
		LevelLoader level;
		level = new LevelLoader(fileName);
		floors.addAll(level.getFloors());
		if (!frame.isStatus()) {
			time = level.getTime() * tmpSett.getTimeScale();
		} else {
			time = level.getTime();
		}
		map.addAll(level.getMap());
		goals.addAll(level.getGoals());
		chests.addAll(level.getChests());
		walls.addAll(level.getWalls());
		player = level.getPlayer();
		mapHeight = level.getMapHeight();
		mapWidth = level.getMapWidth();
		spaceX = width / mapWidth;
		spaceY = (height - 120) / mapHeight;
		getBonus();
		this.setLayout(null);
		this.addKeyListener(this);
		addComponentListener(this);
		frame.addWindowStateListener(this);
		frame.addKeyListener(this);
		frame.add(this);

	}

	/**
	 * zwraca bonus uzyskany przez gracza
	 */
	public void getBonus() {
		String fileName = "Ustawienia.txt";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String tmp = null;
			while ((tmp = reader.readLine()) != null) {
				if (tmp.startsWith("bonuspktmax")) {
					bonusMax = Integer.parseInt(tmp.substring("bonuspktmax".length()));
				}
				if (tmp.startsWith("bonuspktmin")) {
					bonusMin = Integer.parseInt(tmp.substring("bonuspktmin".length()));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (bonusMax < bonusMin) {
			int tmp;
			tmp = bonusMax;
			bonusMax = bonusMin;
			bonusMin = tmp;
		}
		Random rand = new Random();
		int randomNum = rand.nextInt((bonusMax - bonusMin) + 1) + bonusMin;
		for (int i = 0; i < randomNum; i++) {
			timeSpawn.add(rand.nextInt(time - 5) + 5);
		}
	}

	/**
	 * metoda rysujaca poziom
	 */
	public void paint(Graphics g) { // inaczej teraz rysuje bo rysuje podstawe a
									// na niej inne obiekty
		for (int i = 0; i < map.size(); i++) {
			AbstractElement item = (AbstractElement) map.get(i);
			g.drawImage(item.getImage(), item.getX() * (int) spaceX, item.getY() * (int) spaceY, (int) spaceX,
					(int) spaceY, this);
		}
		for (int j = 0; j < chests.size(); j++) {
			ChestElement item2 = (ChestElement) chests.get(j);
			g.drawImage(item2.getImage(), item2.getX() * (int) spaceX + item2.getMoveX(),
					item2.getY() * (int) spaceY + item2.getMoveY(), (int) spaceX, (int) (spaceY), this);
		}
		g.drawImage(player.getImage(), player.getX() * (int) spaceX + moveX, player.getY() * (int) spaceY + moveY,
				(int) spaceX, (int) spaceY, this);
		if (flagBonus)
			g.drawImage(bonno.getImage(), bonno.getX() * (int) spaceX, bonno.getY() * (int) spaceY, (int) spaceX,
					(int) spaceY, this);
	}

	/**
	 * przeciazenie metody z ComponentListner Wyliczna nowe przeliczniki tekstur
	 */
	public void componentResized(ComponentEvent e) {
		Rectangle r = getBounds();
		spaceY = (r.height) / mapHeight;
		spaceX = r.width / mapWidth;
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
			spaceX = (screenSize.getWidth() - 60) / mapWidth;
			spaceY = (screenSize.getHeight() - 50) / mapHeight;
		} else if ((oldState & Frame.MAXIMIZED_BOTH) != 0 && (newState & Frame.MAXIMIZED_BOTH) == 0) {
			Rectangle r = frame.getBounds();
			spaceX = (r.width - 20) / mapWidth;
			spaceY = (r.height - 50) / mapHeight;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) { // w tej metodzie bedziemy wykonywac
											// ruch, bo jest najlatwiej\/
		if (!canMove)
			return;
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = -1;
			move();
			return;
		}
		if (key == KeyEvent.VK_SPACE){
			makePause();
			return;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 1;
			move();
			return;
		}

		if (key == KeyEvent.VK_UP) {
			dy = -1;
			move();
			return;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 1;
			move();
			return;
		}

	}
/**
 * metoda zatrzymujaca gre
 */
	private void makePause() {
		
		
	}

	/**
	 * Metoda odpowiedziala za ruch po planszy Jest wywolywana przez z
	 * keyPressed
	 */
	public void move() {
		if (checkWall(player.getX() + dx, player.getY() + dy)) // sprawdzanie
																// czy element
																// na ktorych
																// chcemy isc
																// czy to nie
																// sciana
			return; // to sciana - wychodzimy z metody
		if (checkChest(player.getX() + dx, player.getY() + dy) >= 0) { // sprawdzenie
																		// czy
																		// dany
																		// element
																		// to
																		// skrzynka
			if (checkWall(player.getX() + dx + dx, player.getY() + dy + dy)) // to
																				// jest
																				// skrzynka,
																				// trzeba
																				// sprawdzic
																				// czy
																				// kolejne
																				// pole
																				// to
																				// sciana
				return; // 2 pole to sciana wychodzimy z metody
			else if (checkChest(player.getX() + dx + dx, player.getY() + dy + dy) >= 0) // sprawdzenie
																						// czy
																						// drugi
																						// element
																						// to
																						// pudelko
				return; // tak to pudelko wychodzimy z metody
			else { // 1 pole to skrzynka 2 pole to pole neutralne
				animacjaGraczaSkrzynki(dx, dy);
				return; // ruszylismy sie to wychodzimy
			}
		}
		animacjaGracza(dx, dy);

	}

	/**
	 * metoda przesuwajaca skrzynie
	 * 
	 * @param dx
	 *            o ile w poziomie
	 * @param dy
	 *            o ile w pionie
	 */
	public void animacjaGraczaSkrzynki(int dx, int dy) {
		canMove = false;
		System.out.println("Pl: " + player.getX() + "  " + player.getY());
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (!canMove & !completed) {
						for (int i = 1; i < 50; i++) {
							moveX = (int) spaceX * dx * i / 50;
							moveY = (int) spaceY * dy * i / 50;
							chests.get(checkChest(player.getX() + dx, player.getY() + dy)).setMoveX(moveX);
							chests.get(checkChest(player.getX() + dx, player.getY() + dy)).setMoveY(moveY);
							frame.repaint();
							Thread.sleep(10);
						}
						moveX = 0;
						moveY = 0;
						chests.get(checkChest(player.getX() + dx, player.getY() + dy)).setMoveX(moveX);
						chests.get(checkChest(player.getX() + dx, player.getY() + dy)).setMoveY(moveY);
						canMove = true;
						chests.get(checkChest(player.getX() + dx, player.getY() + dy)).setXY(player.getX() + dx + dx,
								player.getY() + dy + dy); // ustawienie pozycji
															// nowej skrzynki
						player.setXY(player.getX() + dx, player.getY() + dy); // ustawienie
																				// nowej
																				// pozycji
																				// gracza,
																				// nowa
																				// metoda
																				// ustawiajaca
																				// odrazu
																				// X
																				// i
																				// Y
						checkFinish();

					}
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});
		t.start();
	}


	/**
	 * Metoda odpowiedzialna za animacje gracza
	 * @param dx przesuniecie w poziomie
	 * @param dy przesuniecie w pionie
	 */
	public void animacjaGracza(int dx, int dy) {
		canMove = false;
		System.out.println("Pl: " + player.getX() + "  " + player.getY());
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (!canMove & !completed) {
						for (int i = 1; i < 50; i++) {
							moveX = (int) spaceX * dx * i / 50;
							moveY = (int) spaceY * dy * i / 50;
							frame.repaint();
							Thread.sleep(5);
						}
						moveX = 0;
						moveY = 0;
						canMove = true;
						player.setXY(player.getX() + dx, player.getY() + dy);
					}
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});
		t.start();
	}

	/**
	 * Metoda sprawdzajaca czy warunki ukoczenia mapy sa spelnione
	 */
	public void checkFinish() {
		int tmpCheck = 0;
		for (int i = 0; i < chests.size(); i++) {
			for (int j = 0; j < goals.size(); j++) {
				if (chests.get(i).equalsXY(goals.get(j))) {
					tmpCheck++;
				}
			}
			if (tmpCheck == goals.size()) {
				completed = true;
				System.out.println("level ukonczony");
			}
		}
	}

	/**
	 * @param dx
	 *            chec poruszenia sie na osi ox
	 * @param dy
	 *            chec poruszenia sie na osi oy
	 * @return zwraca czy na drodze nie ma sciany
	 */
	public boolean checkWall(int dx, int dy) // tutaj jest boolean bo tylko tyle
												// nam trzeba
	{
		walls.size();
		WallElement tmp = new WallElement(dx, dy);
		for (int i = 0; i < walls.size(); i++) // sprawdza czy nasz element to
												// ktoras ze scian mapy
		{
			if (tmp.equals(walls.get(i))) // equals jest przeciazony
				return true;
		}
		return false;
	}

	/**
	 * @param dx
	 *            chec przesuniecia sie w osi ox
	 * @param dy
	 *            chec przesuniecia sie w osi oy
	 * @return zwraca polozenie skrzynki z Listy(jezeli zwraca wartosc ujemna
	 *         oznacza to ze na drodze nie ma skrzynki)
	 */
	public int checkChest(int dx, int dy) // tutaj boolean nie wystarczy bo
											// musimy wiedziec jeszcze ktora
											// skrzyka jest na drodze
	{
		ChestElement tmp = new ChestElement(dx, dy);
		for (int i = 0; i < chests.size(); i++) {
			if (tmp.equals(chests.get(i))) {
				return i; // zwraca pozycje skrzynki z list chests, musimy to
							// wiedziec zeby moc zmodyfikowac ja podczas ruchu
			}
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * 
	 * @return informacje czy poziom zostal ukonczony
	 */
	public boolean getCompleted() {
		return completed;
	}

	/**
	 * ustawia flage @param completed na wartosc true - poziom zostal ukonczony
	 */
	public void setCompleted() {
		completed = true;
	}

	/**
	 * 
	 * @return zwraca liste odstepow czasu
	 */
	public ArrayList<Integer> getTimeSpawn() {
		return timeSpawn;
	}

	/**
	 * zmienna odpowiedzialan za wyswietlenie bonusu
	 * 
	 * @param tmp
	 *            zmienna tymczasowa pomocnicza
	 */
	public void spawnBonus(ImagePanel tmp) {
		if (!flagBonus) {
			// System.out.println("jestem tutaj");
			tmpState = tmp;
			Random rand = new Random();
			int spawn = rand.nextInt(floors.size() - 1);
			int bonusX = floors.get(spawn).getX();
			int bonusY = floors.get(spawn).getY();
			bonno = new BonusElement(bonusX, bonusY);
			flagBonus = true;
			frame.repaint();
			frame.revalidate();
			spawnClock(bonusX, bonusY);
			// System.out.println("Bonus X i Y:" + bonusX + " " + bonusY);
		}
	}

	/**
	 * metoda odliczajaca czas widocznosci bonusu
	 * 
	 * @param bonusX
	 *            wspolrzedna pozioma
	 * @param bonusY
	 *            wspolrzedna pionowa
	 */
	public void spawnClock(int bonusX, int bonusY) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int i = 200;
					while (flagBonus) {
						if (bonno.equalsXY(player)) {
							i = 0;
							bonno = null;
							flagBonus = false;
							frame.repaint();
							revalidate();
							tmpState.setBonusPoints();

						}
						if (i == 0) {
							flagBonus = false;
						}
						Thread.sleep(20);
						i--;
					}
					flagBonus = false;

				} catch (Exception ex) {
				}
			}
		});
		t.start();
	}
}

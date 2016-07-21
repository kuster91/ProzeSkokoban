package View;

import Model.Settings;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by DDcreo on 2016-05-14.
 */
public class MyRunnable implements Runnable {
	/**
	 * Zmienna przechowujaca panel gry
	 */
	private GameAreaPanel tmpCurrentArea;
	/**
	 * Zmienna przechowujaca panel wynikow
	 */
	private GameStatePanel tmpCurrentState;
	/**
	 * Zmienna potrzebna do odczytywania listy map
	 */
	private BufferedReader tmpReader;
	/**
	 * zmiennap przechowujaca ekran gry
	 */
	private MainFrame frame;
	/**
	 * Zmienna przechowujaca odczytany w danej chwili nazwe mapy
	 */
	private String tmp = null;
	/**
	 * Zmienne przechowujaca standardowe wymiary mapy
	 * 
	 * DEFAULT_WIDTH szerokosc
	 */
	private int DEFAULT_WIDTH;
	/**
	 * * DEFAULT_HIGHT wysokosc
	 * 
	 */
	private int DEFAULT_HIGHT;
	/**
	 * Zmienna przechowujaca nazwe pliku w ktorym sa nazwy mapy
	 */
	private String mapNames;
	/**
	 * Zmienna informujaca ktora to mapa
	 */
	private int licznik = 0;
	/**
	 * Zmienna przechowujaca aktualny wynik
	 */
	private int score = 0;
	/**
	 * Zmienna przechowujaca ilosc zyc jakie nam pozostaly
	 */
	private int liczbaZyc;
	/**
	 * Przeliczniki do punktacji
	 */
	private int przelicznikCzasu;
	private int przelicznikPunktow;

	private boolean shutdown = false;

	/**
	 * konstruktor klasy
	 * 
	 * @param frame
	 *            ramka z gra
	 * @param DEFAULT_WIDTH
	 *            szerokosc ramki
	 * @param DEFAULT_HIGHT
	 *            wysokosc ramki
	 * @param mapNames
	 *            lista z mapami gry
	 */
	public MyRunnable(MainFrame frame, int DEFAULT_WIDTH, int DEFAULT_HIGHT, String mapNames) {
		this.mapNames = mapNames;
		this.frame = frame;
		this.DEFAULT_HIGHT = DEFAULT_HIGHT;
		this.DEFAULT_WIDTH = DEFAULT_WIDTH;
		Settings ustawienia = new Settings("Ustawienia.txt");
		liczbaZyc = ustawienia.getLives(); // odczytuje z pliku ile mamy zyc
		przelicznikCzasu = ustawienia.getTimeScale();
		przelicznikPunktow = ustawienia.getPointScale();
		try {
			tmpReader = new BufferedReader(new FileReader(mapNames));
			if ((tmp = tmpReader.readLine()) != null) {
				tmpCurrentArea = new GameAreaPanel(tmp, frame, DEFAULT_WIDTH, DEFAULT_HIGHT);
				tmpCurrentState = new GameStatePanel(frame, tmp, liczbaZyc, score, przelicznikCzasu, tmpCurrentArea);
				tmpCurrentArea.repaint();
				tmpCurrentState.getPanel().repaint();
				frame.revalidate();
			} else
				return;
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				tmpReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * uruchomienie watku
	 */
	public void run() {
		try {
			while (!shutdown) {

				if (tmpCurrentArea.getCompleted() == true) {
					wywolanieMapy();
				}
				if (tmpCurrentState.getPanel().getCompleted()) {
					liczbaZyc--;
					if (liczbaZyc < 0) {
						shutdown = true;
						tmpCurrentArea.setCompleted();
						frame.remove(tmpCurrentArea);
						frame.remove(tmpCurrentState.getPanel());
						frame.revalidate();

						JOptionPane.showMessageDialog(null,
								"Gratulacje " + frame.getNickname() + " uzyskales wynik: " + score);
						set10Scores();
						Thread.currentThread().interrupt();

						return;

					}
					
					rebootMapy();
				}
			
				double currentTime=tmpCurrentState.getPanel().getTimeLeft();
				currentTime = currentTime - 0.02;
				tmpCurrentState.getPanel().setTimeLeft(currentTime);
				Thread.sleep(20);
				if(currentTime<0)
				{
					tmpCurrentState.getPanel().setCompleted();	
				}
			}
		} catch (InterruptedException exp) {
			Thread.currentThread().interrupt();
		}

	}

	/**
	 * Ponownie wczytanie tego samego poziomu
	 */
	public void rebootMapy() {
		try {
			tmpReader = new BufferedReader(new FileReader(mapNames));
			for (int i = 0; i < licznik; i++) {
				tmpReader.readLine();
			}
			if ((tmp = tmpReader.readLine()) != null) {
				frame.remove(tmpCurrentArea);

				frame.remove(tmpCurrentState.getPanel());
				tmpCurrentArea = new GameAreaPanel(tmp, frame, DEFAULT_WIDTH, DEFAULT_HIGHT);
				tmpCurrentState = new GameStatePanel(frame, tmp, liczbaZyc, score, przelicznikCzasu, tmpCurrentArea);
				frame.revalidate();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				tmpReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Wczytanie nowego poziomu mapy
	 */
	public void wywolanieMapy() {
		try {
			tmpReader = new BufferedReader(new FileReader(mapNames));
			for (int i = 0; i <= licznik; i++) {
				tmpReader.readLine();
			}
			if ((tmp = tmpReader.readLine()) != null) {
				if (frame.isStatus())
					score += tmpCurrentState.getPanel().getTimeLeft() * przelicznikPunktow
							+ tmpCurrentState.getPanel().getBonusPoints();
				else
					score += tmpCurrentState.getPanel().getTimeLeft() + tmpCurrentState.getPanel().getBonusPoints();
				tmpCurrentState.getPanel().setCompleted();
				licznik++;
				frame.remove(tmpCurrentArea);
				frame.remove(tmpCurrentState.getPanel());
				tmpCurrentArea = new GameAreaPanel(tmp, frame, DEFAULT_WIDTH, DEFAULT_HIGHT);
				tmpCurrentState = new GameStatePanel(frame, tmp, liczbaZyc, score, przelicznikCzasu, tmpCurrentArea);
				frame.revalidate();
			} else {
				if (frame.isStatus())
					score += tmpCurrentState.getPanel().getTimeLeft() * przelicznikPunktow
							+ tmpCurrentState.getPanel().getBonusPoints();
				else
					score += tmpCurrentState.getPanel().getTimeLeft() + tmpCurrentState.getPanel().getBonusPoints();
				shutdown = true;
				tmpCurrentArea.setCompleted();
				frame.remove(tmpCurrentArea);
				frame.remove(tmpCurrentState.getPanel());
				frame.revalidate();

				JOptionPane.showMessageDialog(null,
						"Gratulacje " + frame.getNickname() + " ukonczyles wszystkie mapy z wynikiem: " + score);
				set10Scores();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				tmpReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ustawienie 10 najlepszych wynikow
	 */
	public void set10Scores() {
		String fileName = "info\\highScore.txt";
		BufferedReader reader = null;
		BufferedWriter writer = null;
		ArrayList<String> tmpList = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String tmp = null;
			while ((tmp = reader.readLine()) != null) {
				tmpList.add(tmp);
			}

			for (int i = 2; i < tmpList.size(); i++) {

				if (i > 12)
					break;
				String[] parts = tmpList.get(i).split(" ");
				int tmpScore = Integer.parseInt(parts[0]);
				if (score > tmpScore) {
					tmpList.add(i, score + " " + frame.getNickname());
					break;
				}
			}
			for (int i = 0; i < tmpList.size(); i++) {
				System.out.println(tmpList.get(i));
			}
			// if (tmpList.size() < 10)
			// for (int i = 0; i < tmpList.size() + 1; i++) {
			// if (i == tmpList.size()) {
			// // Tutaj wstawiamy nicname
			// tmpList.add(i, "NickName " + score);
			// break;
			// }
			// String[] parts = tmpList.get(i).split(" ");
			// int tmpScore = Integer.parseInt(parts[1]);
			// if (score > tmpScore) {
			// // tutaj wstawiamy nickname
			// tmpList.add(i, "NickName666 " + score);
			// break;
			// }
			//
			// }
			// else {
			// for (int i = 0; i < tmpList.size(); i++) {
			// String[] parts = tmpList.get(i).split(" ");
			// int tmpScore = Integer.parseInt(parts[1]);
			// if (score > tmpScore) {
			// tmpList.add(i, "NickName666 " + score);
			// tmpList.remove(tmpList.size() - 1);
			// break;
			// }
			//
			// }
			// }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			for (int i = 0; i < tmpList.size(); i++) {
				writer.write(tmpList.get(i));
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}

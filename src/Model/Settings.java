package Model;

/**
 * klasa odczytujaca ustawienia z pliku
 * <p>
 * lives liczba zyc pointScale przelicznnik punktow timeScale przelicznik czasu
 * dostepnego na poziom
 */
public class Settings {
	/**
	 * lives przechowuje odczytana wartosc ilosci posiadanych zyc przez
	 *       gracza
	 */
	private static int lives;
	/**
	 * pointScale przechowuje odczytana wartosc przelicznika punktow
	 */
	private static int pointScale;
	/**
	 * timeScale przechowuja odczytana wartosc przeskalowania czasu
	 */
	private static int timeScale;

	/**
	 * parametry potrzebne przy wyszukiwaniu odczytywanych wartosci
	 * 
	 * DEFAULT_TIME
	 *            - czas gry
	 * DEFAULT_POINT_SCALE
	 *            - przelcznik punktow
	 * DEFAULT_LIVES
	 *            - przypisane zycia
	 */
	private final static String DEFAULT_TIME = "czas";
	private final static String DEFAULT_POINT_SCALE = "punkty";
	private final static String DEFAULT_LIVES = "lives";

	public Settings(String nazwaPliku) {
		/**
		 * Otworznie pliku z ustawieniami gry
		 */
		DataFileReader settingFile = new DataFileReader(nazwaPliku);
		/**
		 * tymczasowy string przechowujacy odczytany wiersz
		 */
		String tmp;
		for (int i = 0; i < settingFile.getDataList().size(); i++) {
			tmp = settingFile.getDataList().get(i);
			/**
			 * Case1 - w pliku znajduje sie dany string i posiada przypisana
			 * wartosc
			 */
			if (tmp.startsWith(DEFAULT_LIVES) && tmp.length() > DEFAULT_LIVES.length()) {
				/**
				 * przypisanie odczytanej wartosci
				 */
				lives = Integer.parseInt(tmp.substring(DEFAULT_LIVES.length()));
				break;
				/**
				 * Case2 - w pliku znaduje sie dany string ale nie ma
				 * przypisanych wartosc
				 */
			} else if (tmp.equals(DEFAULT_LIVES)) {
				lives = 3;
				break;
				/**
				 * Case3 - w pliku nie ma danego stringu
				 */
			} else
				lives = 3;
		}

		for (int i = 0; i < settingFile.getDataList().size(); i++) {
			tmp = settingFile.getDataList().get(i);
			/**
			 * Case1 - w pliku znajduje sie dany string i posiada przypisana
			 * wartosc
			 */
			if (tmp.startsWith(DEFAULT_POINT_SCALE) && tmp.length() > DEFAULT_POINT_SCALE.length()) {
				pointScale = Integer.parseInt(tmp.substring(DEFAULT_POINT_SCALE.length()));
				break;
				/**
				 * Case2 - w pliku znaduje sie dany string ale nie ma
				 * przypisanych wartosc
				 */
			} else if (tmp.equals(DEFAULT_POINT_SCALE)) {
				pointScale = 2;
				break;
				/**
				 * Case3 - w pliku nie ma danego stringu
				 */
			} else
				pointScale = 2;
		}
		for (int i = 0; i < settingFile.getDataList().size(); i++) {
			tmp = settingFile.getDataList().get(i);
			/**
			 * Case1 - w pliku znajduje sie dany string i posiada przypisana
			 * wartosc
			 */
			if (tmp.startsWith(DEFAULT_TIME) && tmp.length() > DEFAULT_TIME.length()) {
				timeScale = Integer.parseInt(tmp.substring(DEFAULT_TIME.length()));
				break;
				/**
				 * Case2 - w pliku znaduje sie dany string ale nie ma
				 * przypisanych wartosc
				 */
			} else if (tmp.equals(DEFAULT_TIME)) {
				timeScale = 2;
				break;
				/**
				 * Case3 - w pliku nie ma danego stringu
				 */
			} else
				timeScale = 2;
		}

		/**
		 * Sprawdzenie czy przypisane wartosc sa logiczne
		 */
		if (lives < 0)
			lives = 3;
		if (pointScale < 1)
			pointScale = 2;
		if (timeScale < 1)
			timeScale = 2;
	}

	/**
	 *
	 * @return zwraca ilosc zyc gracza
	 */
	public int getLives() {
		return lives;
	}

	/**
	 *
	 * @return zwraca warosc przeskalowania punktow
	 */
	public int getPointScale() {
		return pointScale;
	}

	/**
	 *
	 * @return zwarac wartosc przelicznika czasu
	 */
	public int getTimeScale() {
		return timeScale;
	}

}
package Model;

/**
 * klasa odczytujaca ustawienia z pliku
 *
 *  lives liczba zyc
 *  pointScale przelicznnik punktow
 *  timeScale przelicznik czasu dostepnego na poziom
 */
public class Settings {
	private static int lives;
	private static int pointScale;
	private static int timeScale;

	private final static String DEFAULT_TIME = "czas";
	private final static String DEFAULT_POINT_SCALE = "punkty";
	private final static String DEFAULT_LIVES = "lives";

	public Settings(String nazwaPliku) {
		DataFileReader settingFile = new DataFileReader(nazwaPliku);
		String tmp;
		for (int i = 0; i < settingFile.getDataList().size(); i++) {
			tmp = settingFile.getDataList().get(i);
			if (tmp.startsWith(DEFAULT_LIVES) && tmp.length() > DEFAULT_LIVES.length()) {
				lives = Integer.parseInt(tmp.substring(DEFAULT_LIVES.length()));
				break;
			} else if (tmp.equals(DEFAULT_LIVES)) {
				break;
			} else
				lives = 3;
		}

		for (int i = 0; i < settingFile.getDataList().size(); i++) {
			tmp = settingFile.getDataList().get(i);

			if (tmp.startsWith(DEFAULT_POINT_SCALE) && tmp.length() != DEFAULT_POINT_SCALE.length()) {
				pointScale = Integer.parseInt(tmp.substring(DEFAULT_POINT_SCALE.length()));
				break;
			} else if (tmp.equals(DEFAULT_POINT_SCALE)) {
				pointScale = 2;
				break;
			} else
				pointScale = 2;
		}
		for (int i = 0; i < settingFile.getDataList().size(); i++) {
			tmp = settingFile.getDataList().get(i);

			if (tmp.startsWith(DEFAULT_TIME) && tmp.length() != DEFAULT_TIME.length()) {
				break;
			} else if (tmp.equals(DEFAULT_TIME)) {
				timeScale = 2;
				break;
			} else
				timeScale = 2;
		}
		if (lives <= 0)
			lives = 3;
		if (pointScale < 1)
			pointScale = 2;
		if (timeScale < 1)
			timeScale = 2;
	}

	public int getLives() {
		return lives;
	}

	public int getPointScale() {
		return pointScale;
	}

	public int getTimeScale() {
		return timeScale;
	}

}
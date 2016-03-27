package Model;

public class Settings {
	private static int lives; // przechowuje ilosc zyc
	private static int pointScale; // mnoznik punktow
	private static int timeScale; // mnoznik dodatkowe czasu na poziom

	public Settings(String nazwaPliku) {
		DataFileReader settingFile = new DataFileReader(nazwaPliku); // odczytanie
																		// pliku
		String tmp;

		for (int i = 0; i < settingFile.getDataList().size(); i++) { // zycia
			tmp = settingFile.getDataList().get(i);
			if (tmp.startsWith("lives") && tmp.length() != 5) // sprawdza czy
																// znajduje
																// slowo lives w
																// odczytanych
																// plikach
			{ // sprawdza czy jest jakas wartosc lives
				lives = Integer.parseInt(tmp.substring(5));
				break;
			} else if (tmp.equals("lives")) {
				lives = 3;
				break;
			} else
				lives = 3; // jezeli nie znalazlo lives lub nie ma okreslonego
							// domyslnie przypisuje 3

		}

		for (int i = 0; i < settingFile.getDataList().size(); i++) { // punkty
			tmp = settingFile.getDataList().get(i);
			if (tmp.startsWith("punkty") && tmp.length() != 6) // sprawdza czy
																// znajduje
																// slowo punty w
																// odczytanych
																// plikach
			{ // sprawdza czy jest jakas wartosc punktow
				pointScale = Integer.parseInt(tmp.substring(6));
				break;
			} else if (tmp.equals("punkty")) {
				pointScale = 2;
				break;
			} else
				pointScale = 2;
		}

		for (int i = 0; i < settingFile.getDataList().size(); i++) { // czas
			tmp = settingFile.getDataList().get(i);
			if (tmp.startsWith("czas") && tmp.length() != 4) // sprawdza czy
																// znajduje
																// slowo czas w
																// odczytanych
																// plikach
			{ // sprawdza czy jest jakas wartosc czasu
				timeScale = Integer.parseInt(tmp.substring(4));
				break;
			} else if (tmp.equals("czas")) {
				timeScale = 2;
				break;
			} else
				timeScale = 2;
		}
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

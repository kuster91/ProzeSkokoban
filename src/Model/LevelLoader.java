package Model;

import java.util.ArrayList;
/**
 * Klasa odpowiedzialna za stworzenie obiektów mapy
 *
 *  DEFAULT_PLAYER gracz w pliku mapy
 *  DEFAULT_WALL œciana w pliku mapy
 *  DEFAULT_GOAL dziura na skrzynke w pliku mapy
 *  DEFAULT_FLOOR podloga w pliku mapy
 *  DEFAULT_CHEST skrzynia w pliku mapy
 *  time czas na przejscie poziomu
 *  map lista obiektow poziomu
 *  mapWidth szerokosc mapy
 *  mapHeight wysokosc mapy
 */
public class LevelLoader {
	/**
	 *
	 */
	private final static char DEFAULT_PLAYER = '@';
	private final static char DEFAULT_WALL = '#';
	private final static char DEFAULT_GOAL = 'o';
	private final static char DEFAULT_FLOOR = '_';
	private final static char DEFAULT_CHEST = '$';

	private int time;
	private ArrayList<AbstractElement> map = new ArrayList<>();
	private int mapWidth;
	private int mapHeight;
/**
 *
 * @param fileName sciezka pliku z mapa
 */
	public LevelLoader(String fileName) {
		DataFileReader file = new DataFileReader(fileName);
		time = Integer.parseInt(file.getDataList().get(0));
		mapHeight = 0;
		mapWidth =0;
		char item;
		for (int i = 1; i < file.getDataList().size(); ++i) {
			mapHeight++;
			mapWidth = 0;
			for (int j = 0; j < file.getDataList().get(i).length(); ++j) {
				mapWidth++;
				item = file.getDataList().get(i).charAt(j);
				if (item == DEFAULT_PLAYER) // player
				{
					map.add(new PlayerElement(j, i - 1));
				} else if (item == DEFAULT_WALL)// wall
				{
					map.add(new WallElement(j, i - 1));
				} else if (item == DEFAULT_CHEST) // box
				{
					map.add(new ChestElement(j, i - 1));
				} else if (item == DEFAULT_GOAL) // goal-spot
				{
					map.add(new GoalElement(j, i - 1));
				} else if (item == DEFAULT_FLOOR) // floor
				{
					map.add(new FloorElement(j, i - 1));
				}
			}
		}
	}

	/**
	 *
	 * @return ArrayList zawierajacy obiekty z ktorych zbudowana jest mapa
	 */
	public ArrayList<AbstractElement> getMap() {
		return map;
	}
/**
 *
 * @return Czas na przejscie mapy;
 */
	public int getTime() {
		return time;
	}
	/**
	 *
	 * @return szerokoœc mapy
	 */
	public int getMapWidth() {
		return mapWidth;
	}

	/**
	 *
	 * @return wysokoœc mapy
	 */
	public int getMapHeight() {
		return mapHeight;
	}
}
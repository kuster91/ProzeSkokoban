package Model;

import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za stworzenie obiekt�w mapy
 * <p>
 * DEFAULT_PLAYER gracz w pliku mapy
 * DEFAULT_WALL �ciana w pliku mapy
 * DEFAULT_GOAL dziura na skrzynke w pliku mapy
 * DEFAULT_FLOOR podloga w pliku mapy
 * DEFAULT_CHEST skrzynia w pliku mapy
 * time czas na przejscie poziomu
 * map lista obiektow poziomu
 * mapWidth szerokosc mapy
 * mapHeight wysokosc mapy
 */
public class LevelLoader {
    /**
     * '@' - zmienna opisujaca polozenie gracza w pliku txt
     */
    private final static char DEFAULT_PLAYER = '@';
    /**
     * # - zmienna opisujaca polozenie sciany w pliku txt
     */
    private final static char DEFAULT_WALL = '#';
    /**
     *  o - zmienna opisujaca polozenie celu Mapy w pliku txt
     */
    private final static char DEFAULT_GOAL = 'o';
    /**
     * _ - zmienna opisujaca polozenie podlogi w pliku txt
     */
    private final static char DEFAULT_FLOOR = '_';
    /**
     * $ - zmienna opisujaca polozenie skrzynki w pliku txt
     */
    private final static char DEFAULT_CHEST = '$';
    /**
     * zmienna przechowujaca czas na wykonanie celow mapy
     */
    private int time;
    /**
     * Lista przechowujaca wszystkie elemnty na mapie
     */
    private ArrayList<AbstractElement> map = new ArrayList<>();
    /**
     * Lista przechowujaca Chest elementy
     */
    private ArrayList<ChestElement> chests = new ArrayList<>();
    /**
     * Lista przechowujaca Cele mapy
     */
    private ArrayList<GoalElement> goals = new ArrayList<>();
    /**
     * Lista przechowujaca sciany mapy
     */
    private ArrayList<WallElement> walls = new ArrayList<>();
    /**
     * Zmienna przechowujaca ilosc kolun mapy
     */
    private int mapWidth;
    /**
     * Zmienna przechowujac ilosc wierszy mapy
     */
    private int mapHeight;
    /**
     * Zmienna skalujaca tekstury w poziomoe
     */
    /**
     * zmienne przechowujace polozenie gracza
     */
    private PlayerElement player;
    /**
     * Lista przechowujaca podlogi mapy
     */
    private ArrayList<FloorElement> floors = new ArrayList<>();

    /**
     * @param fileName sciezka pliku z mapa
     */
    public LevelLoader(String fileName) {
        /**
         * Odczytanie pliku zawierajacego ustawienia mapy
         */
        DataFileReader file = new DataFileReader(fileName);
        time = Integer.parseInt(file.getDataList().get(0));
        mapHeight = file.getDataList().size() - 1 ;
        char item;
        for (int i = 1; i < file.getDataList().size(); ++i) {
            /**
             * ustawienie wysokosci mapy na podstawie ilosc odczytanych wierszy
             */
            for (int j = 0; j < file.getDataList().get(i).length(); ++j) {
                /**
                 * Ustawienie szerokosc mapy na podstawie ilosc znakow w wierszu
                 */
                if(mapWidth < j+1) {mapWidth = j+1;}
                /**
                 * Odczytanie kolejnych znakow w wierszu
                 */
                item = file.getDataList().get(i).charAt(j);
                /**
                 * Jezeli odczytany znak to @  zostanie dodanie gracza do listy
                 */
                if (item == DEFAULT_PLAYER) // player
                {
                    floors.add(new FloorElement(j,i-1));
                    player = new PlayerElement(j, i - 1);
                    map.add(new FloorElement(j, i - 1));

                }
                /**
                 * Jezeli odczytany znak to # zostanie dodana sciana do listy
                 */
                else if (item == DEFAULT_WALL)// wall
                {
                    walls.add(new WallElement(j, i - 1));
                    map.add(new WallElement(j, i - 1));
                }
                /**
                 * Jezeli odczytany znak to $  zostanie dodanie skrzynki do listy
                 */
                else if (item == DEFAULT_CHEST) // box
                {
                    floors.add(new FloorElement(j,i-1));
                    map.add(new FloorElement(j, i - 1));
                    chests.add(new ChestElement(j,i-1));
                }
                /**
                 * Jezeli odczytany znak to o  zostanie dodanie cel do listy
                 */
                else if (item == DEFAULT_GOAL) // goal-spot
                {
                    floors.add(new FloorElement(j,i-1));
                    map.add(new GoalElement(j, i - 1));
                    goals.add(new GoalElement(j,i-1));
                }
                /**
                 * Jezeli odczytany znak to _  zostanie dodanie podlogi do listy
                 */
                else if (item == DEFAULT_FLOOR) // floor
                {
                    floors.add(new FloorElement(j,i-1));
                    map.add(new FloorElement(j, i - 1));
                }
            }
        }

    }

    /**
     * @return ArrayList zawierajacy obiekty z ktorych zbudowana jest mapa
     */
    public ArrayList<AbstractElement> getMap() {
        return map;
    }

    /**
     * @return Czas na przejscie mapy;
     */
    public int getTime() {
        return time;
    }

    /**
     * @return szeroko�c mapy
     */
    public int getMapWidth() {
        return mapWidth;
    }

    /**
     * @return wysoko�c mapy
     */
    public int getMapHeight() {
        return mapHeight;
    }

    /**
     *
     * @return Zwraca polozenia gracza na mapie
     */
    public PlayerElement getPlayer() { return player;   }

    /**
     *
     * @return Zwraca liste skryn na mapie
     */
    public ArrayList<ChestElement> getChests(){return chests;}

    /**
     *
     * @return Zwraca liste celów mapy
     */
    public ArrayList<GoalElement> getGoals () {return  goals;}

    /**
     *
     * @return Zwraca list scian na mapie
     */
    public ArrayList<WallElement> getWalls () {return walls;}

    /**
     *
     * @return Zwraca liste podlog na mapie
     */
    public ArrayList<FloorElement> getFloors() {
        return floors;
    }
}


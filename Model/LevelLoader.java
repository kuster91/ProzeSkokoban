package Model;

import java.util.ArrayList;

public class LevelLoader {
	private int time;
	private Player player;
	private ArrayList<Wall> walls;
	private ArrayList<Chest> chests;
	private ArrayList<Goal> goals;
	private ArrayList<Floor> floors;

	public LevelLoader(String fileName) {
		DataFileReader file = new DataFileReader(fileName);

		time = Integer.parseInt(file.getDataList().get(0));

		for (int i = 1; i < file.getDataList().size(); ++i) {
			for(int j=0; j<file.getDataList().get(i).length() ; ++j)
			{
				char item = file.getDataList().get(i).charAt(i);
				if(item == '@') // player
				{
					player = new Player(j, i-1);
				}
				else if (item == '#')// wall
				{
					Wall wall = new Wall(j, i-1);
					walls.add(wall);
				}
				else if(item == '$') //box
				{
					Chest box = new Chest(j, i-1);
					chests.add(box);
				}
				else if (item == 'o') // goal-spot
				{
					Goal goal = new Goal ( j, i-1);
					goals.add(goal);
				}
				else if (item == '_') // floor
				{
					Floor floor = new Floor(j,i-1);
					floors.add(floor);
				}
			}
		}
	}
	public ArrayList<Wall> getWalls() {
		return walls;
	}
	public ArrayList<Chest> getChest() {
		return chests;
	}
	public ArrayList<Goal> getGoal() {
		return goals;
	}
	public ArrayList<Floor> getFloor() {
		return floors;
	}
	public int getTime() {
		return time;
	}
	public Player getPlayer() {
		return player;
	}
}

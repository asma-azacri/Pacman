package PacmanModel;

import java.awt.Color;

public class RoomSimple extends Room{

	public RoomSimple(Maze maze, Coordinate location) {
		super(maze, location, Color.BLACK);
	}

	@Override
	public void entrer(Personnage personnage) {
		personnage.ChangeRoom(this);
	}

}

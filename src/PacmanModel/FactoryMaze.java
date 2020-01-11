package PacmanModel;

import java.awt.Color;

public class FactoryMaze {
	public static enum TypeRoom { RoomMajic, RoomSimple };
	
	public static Maze makeMaze(Game game){
		return new Maze(game);
	}
	
	public static Wall makeWall(){
		return new Wall();
	}
	public static Room makeRoom(TypeRoom typeRoom, Maze maze, int i, int j){
		if(typeRoom.equals(TypeRoom.RoomMajic))
		return new RoomMajic(maze, new Coordinate(i,j));
		else if(typeRoom.equals(TypeRoom.RoomSimple))
				return new RoomSimple(maze, new Coordinate(i,j));
		else 
			return null;

	}
	public static Door makeDoor(Room a, Room b){
		return new Door(a,b,true);
	}
	
	public static PacGomme makeSimplePacGomme(Game game, Room room, Color colorP, int point){
		return  new SimplePacGomme(game, room, colorP,  point);
	}
	
	public static PacGommeActive makePacGommeActive(Game game, Room room, Color colorP, int point, Effect effet){
		return new PacGommeActive(game,room,colorP,point, effet);
	}

}

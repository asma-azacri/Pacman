   package PacmanModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import PacmanModel.FactoryMaze.TypeRoom;


public class MazeGame {
	
	private   Maze maze;
	private  Stack<Room> stack ; 
	private  ArrayList<Room> listVoisin ;
	private Game game;
	public MazeGame(Game game){
		this.game = game;
		maze = FactoryMaze.makeMaze(game);
		this.stack = new Stack<Room>();
		this.listVoisin = null;
	}
	
	/**
	 * 3]  Methode visite récursivement la pièce non visitée et brise le mur avec son voisin possible
	 * @param index
	 */
	private void visiter(int index)   {
		Room room = maze.findRoom(index);
		room.visited(true);
		listVoisin = room.getVoisins(maze.getListRoom());
		if(listVoisin != null && !listVoisin.isEmpty() ) {
			Collections.shuffle(listVoisin);
			Room voisin = listVoisin.get(0);
			stack.push(voisin);
			Direction direction  = room.getDirection(voisin);
			Door door= FactoryMaze.makeDoor(room, voisin);
			switch(direction){
				case Nord :				room.setDoor(Direction.Nord, door);
										voisin.setDoor(Direction.Sud, door);									
										break;
				case Est:				room.setDoor(Direction.Est, door);
										voisin.setDoor(Direction.Ouest, door);
										break;
				case Sud:				room.setDoor(Direction.Sud, door);
										voisin.setDoor(Direction.Nord, door);
										break;				
				case Ouest:				room.setDoor(Direction.Ouest, door);
										voisin.setDoor(Direction.Est, door);
										break;
				default:
					break;	
			}
			listVoisin = null;
			visiter(stack.lastElement().getRoomId());
		} else {
			stack.pop();
			if(stack.isEmpty())
				return;
			visiter(stack.lastElement().getRoomId());
		}
	}
	
	/**
	 *  2] Methode parcours les pièces de maze non visitées
	 * @param maze
	 */
   public  void parcourir(Maze maze){	   
	   if(!maze.findRoom(1).estVisited()) { 
		   stack.push(maze.findRoom(1));
		   visiter(stack.lastElement().getRoomId());
	   }
   }
	
  /**
   *  1] Methode crée un certaine nombre de pièces entourées par un mur dans les 4 cotés  	
   */
   private void initialisation() {
	   	Room room = null;
		for(int i = 0; i < 8; i++){
		 for(int j = 0; j < 8; j++){
			 if((i == 7 && j == 0) || (i == 0 && j == 7)) {
				 room = FactoryMaze.makeRoom(TypeRoom.RoomMajic, maze, i, j);
			 }
			 else 
			 room = FactoryMaze.makeRoom(TypeRoom.RoomSimple, maze, i, j);
			 room.setside(Direction.Nord, FactoryMaze.makeWall());
			 room.setside(Direction.Est, FactoryMaze.makeWall());
			 room.setside(Direction.Sud, FactoryMaze.makeWall());
			 room.setside(Direction.Ouest, FactoryMaze.makeWall());
			 maze.addRoom(room);
		 } 
		}
   }
   	
	public  Maze generate()   {
		parcourir(maze);
		return maze;
	}
	
	public Maze createMaze()  {
		initialisation();
		maze = generate();
		return maze;
		
	}


	public Game getGame()
	{
		return game;
	}
	

}

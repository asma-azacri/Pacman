package PacmanModel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import PacmanModel.Door.Sens;

public abstract class Room implements MapSite {
	
	private Map<Direction, MapSite> mapSite = new HashMap<>();
	private Coordinate location;
	private Color Color_Room;
	private boolean visited;
	private PacGomme pacgomme;
	private static int cmpt_instance = 0;
	private int id;
	private Maze maze;

	public Room(Maze maze,Coordinate location, Color colorRoom){
		this.location = location;
		this.Color_Room = colorRoom;
		visited = false;
		id = cmpt_instance;
		cmpt_instance += 1; 
		this.maze = maze;
	}
	
	/**
	 *  méthode qui réinitialise une piéce en mettant qu'elle n'est pas visité et qu'elle est entouré pas le mur   
	 */
	public void resetRoom() {
		this.visited = false;
		setside(Direction.Nord, FactoryMaze.makeWall());
		setside(Direction.Est, FactoryMaze.makeWall());
		setside(Direction.Sud, FactoryMaze.makeWall());
		setside(Direction.Ouest, FactoryMaze.makeWall());
	}
	public Maze getMaze() {
		return this.maze;
	}
	public void setColor(Color colorRoom){
		this.Color_Room = colorRoom;
	}
	public int getRoomId(){
		return id;
	}
	public void visited(boolean visited){
		this.visited = visited;
	}
	public void setside(Direction direction, MapSite site){
		if(site instanceof Door){
			Door door = (Door) site;
			if(direction.equals(Direction.Nord) || direction.equals(Direction.Sud))
				door.setSens(Sens.Vertical);	
			else if(direction.equals(Direction.Est) || direction.equals(Direction.Ouest))
				door.setSens(Sens.Horisental);
		}
		mapSite.put(direction, site);
	}
	
	private boolean estVoisin(Room voisin){
				// Voisin sur le coté Nord
		return ( voisin.location.getX() == location.getX()      && 
				 voisin.location.getY()== location.getY() - 1 )      ||
				// voisin sur le coté Est                                  
				( voisin.location.getX() == location.getX() + 1 &&
				 voisin.location.getY()== location.getY())           ||																   				
				// voisin sur le sud	
				(voisin.location.getX() == location.getX()      &&
				voisin.location.getY()== location.getY() + 1)		 ||
				// voisin sur le Ouest
				(  voisin.location.getX() == location.getX() -1 &&
				voisin.location.getY()== location.getY());
	}
	
	public ArrayList<Room> getVoisins(ArrayList<Room> rooms){
		ArrayList<Room> voisins = new ArrayList<Room>();
		if(rooms != null){
			for(Room r : rooms){  
				if(estVoisin(r) && !r.estVisited())		
					voisins.add(r);
			}
			return voisins;
		}
		else
			return null;
	}
	public Room move(Direction dire) {
		if(mapSite.containsKey(dire)) {
			if(mapSite.get(dire) instanceof Door) {
				Door door = (Door) mapSite.get(dire);
				return door.chemin(this);
			}
			else if(mapSite.get(dire) instanceof Wall){
					this.maze.getPacman().reflexion();
					for(Personnage ghost : maze.getFantomes()) {
						ghost.reflexion();
					}
					return this;
			}
		}
		return null;
	}
	public Direction getDirection(Room voisin){
		if(voisin != null){
			if(this.estVoisin(voisin)){
				// Voisin sur le coté Nord
				if(voisin.location.getX() == location.getX()            &&
						voisin.location.getY() == location.getY() - 1)
						return Direction.Nord;
			
				// voisin sur le coté Est
				else if(voisin.location.getX() == location.getX() + 1   &&
						voisin.location.getY() == location.getY())
						return Direction.Est;
			
				// voisin sur le sud
				else if(voisin.location.getX() == location.getX()       &&
						voisin.location.getY()== location.getY() + 1)
						return Direction.Sud;
				// voisin sur le Ouest
				else if(voisin.location.getX() == location.getX() - 1   &&
					voisin.location.getY()== location.getY())
					return Direction.Ouest;
			}
		}
		return null;
	}
	public boolean estVisited(){
		return visited;
	}
	public void location(Coordinate location){
		this.location = location;
	}
	
	public Coordinate getLocation(){
		return location;
	}

	public MapSite getSite(Direction dir) {
		if(mapSite.containsKey(dir)) {
			return mapSite.get(dir);
		}
		return this;
	}

	

	public void setDoor(Direction direction, Door door){
		setside(direction, door);
	}
	
	public void removePacgomme(){
		maze.removePacGomme(pacgomme);
		this.pacgomme = null;
	}
	public void setPacgomme(PacGomme pacgomme){
		this.pacgomme = pacgomme;
		maze.setPacGomme(pacgomme);
	}
	public PacGomme getPacGomme(){
		return this.pacgomme;
	}
	public boolean existPacGomme(){
		return pacgomme != null;
	}

	
	@Override
	public void draw(Graphics g, int x, int y, int w, int h) {
		  g.setColor(Color_Room); 
		  g.fillRect(x, y, w, h); 
	}
	public abstract void entrer(Personnage personnage);
}

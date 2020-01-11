package PacmanModel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public abstract class Personnage implements Element {
	
	protected Room currentRom ;
	protected Direction direction;
	protected  List<PersonnageObserver> observers;
	protected Color coloPersonnage;
	protected  Game game;
	protected Direction memoryDirection;
	protected Room startRoom;
	
	public Personnage(Room room, Direction dir, Game game, Color colorPersonnage){
		this.coloPersonnage = colorPersonnage;
		currentRom = room;
		startRoom = currentRom;
		direction = dir;
		this.game = game;
		memoryDirection = dir;
		observers  = new  ArrayList<>();
	}
	
	public void register(PersonnageObserver o){	
		observers.add(o);
	}
	
	public void reset(){
		currentRom = startRoom;
	}
	public Room getCurrentRoom(){
		return this.currentRom;
	}

	public void setDirection(Direction direction){	
		this.direction = direction;
	}
	public Direction getDirection(){
		return direction;
	}
	
	public Game getGame() {
		return game;
	}
	
	public void changeColor(Color colorPer)
	{
		this.coloPersonnage = colorPer;
	}
	
	public abstract void ChangeRoom(Room room);
	protected abstract  void notifyObserver(List<Room> events);
	public abstract Room move();
	public abstract void reflexion();
	public abstract void die();

	
}

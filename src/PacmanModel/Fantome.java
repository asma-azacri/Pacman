package PacmanModel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Fantome extends Personnage {
	
	private static final int nbInstance = 4;
	private static  int compteurIntancce = 0;	
	private Color colorInitial;
	private Fantome(Game game, Color fantomeColor, Room entree, Direction dir) {
		super(entree, dir, game,fantomeColor);
		startRoom = this.currentRom;
		this.colorInitial = this.coloPersonnage;
	}
	
	public static Fantome creerFantome(Game game, Color fantomeColor, Room entree, Direction dir){
		if(compteurIntancce < nbInstance){
			compteurIntancce++;
			return  new Fantome(game, fantomeColor, entree,dir); 
		} else {
		  return null;
		}
	}
	@Override
	public Room move() {
		Room next = this.currentRom.move(direction);
		return next;
	}

	/**
	 * 
	 * @return  Direction aléatoire
	 */
	private Direction aleatoire(){
		ArrayList<Direction> listd = new ArrayList<>();
		listd.add(Direction.Nord);
		listd.add(Direction.Ouest);
		listd.add(Direction.Sud);
		listd.add(Direction.Est);
		Collections.shuffle(listd);
		return listd.get(0);
	}
	@Override
	public void reflexion() {
		this.setDirection(aleatoire());
	}

	
	@Override
	protected void notifyObserver(List<Room> events) {
		for(PersonnageObserver personnageObserver : observers)
			personnageObserver.notifyFantomeEvent(events);
		
	}
	@Override
	public void ChangeRoom(Room room) {
		this.currentRom = room;
		List<Room> events = new ArrayList<>();
		events.add(currentRom);
		notifyObserver(events);	
	}
	
	@Override
	public void die() {
		this.currentRom = this.startRoom;
	}
	public void resetColor() {
		this.coloPersonnage = colorInitial;
	}
	public void setColor(Color color) {
		this.coloPersonnage = color;
	}
	@Override
	public void draw_element(Graphics g, int Scale) {
		g.setColor(this.coloPersonnage);
		g.fillOval(this.currentRom.getLocation().getX() * Scale + 20, currentRom.getLocation().getY() * Scale + 20, Scale / 3, Scale / 3);	
		
	}

}

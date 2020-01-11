package PacmanModel;

import java.awt.Color;
import java.awt.Graphics;

public class SimplePacGomme implements PacGomme {
	
	private Room room;
	private Color colorP;
	private Game game;
	private int point;
	
	public SimplePacGomme(Game game, Room room, Color colorP, int point) {
		this.game = game;
		this.room = room;
		this.colorP = colorP;
		this.point = point;
	}
	

	@Override
	public void addScor(Pacman pacman) {
		pacman.getCompteur().upDateScore(point);
	}
	
	@Override
	public void die(){
		room.removePacgomme();
	}

	@Override
	public void setRoom(Room room){
		this.room = room;
	}
	@Override
	public Room getRoom() {
		return this.room;
	}
	
	@Override
	public Game getGame(){
		return this.game;
	}
	
	@Override
	public void draw_pacGomme(Graphics g, int Scale) {
		g.setColor(colorP);
		g.fillOval(room.getLocation().getX() * Scale + 25 , room.getLocation().getY()* Scale + 25,10, 10);
		
	}

}

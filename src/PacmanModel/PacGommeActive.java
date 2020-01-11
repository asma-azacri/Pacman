package PacmanModel;

import java.awt.Color;
import java.awt.Graphics;

public class PacGommeActive implements PacGomme {
    
	private PacGomme pacgomme;
	private Effect effets;
	private int dure ;
	public PacGommeActive(Game game, Room room, Color coloP, int point, Effect effet){
		pacgomme = new SimplePacGomme(game,room, coloP,point);
		this.effets = effet;
		dure = effet.getDuree();
	}
	
	int getDureeEffect(){
		return this.dure;
	}
	
	public void launcheEffect(){
		this.effets.effect();
	}
	
	public void addScor(Pacman pacman){
		pacgomme.addScor(pacman);
	}
	
	public void die(){
		pacgomme.die();
	}
	

	@Override
	public Room getRoom() {
		return pacgomme.getRoom();
	}
	@Override
	public void setRoom(Room room) {
		this.pacgomme.setRoom(room);
		
	}

	@Override
	public Game getGame() {
		return pacgomme.getGame();
	}

	public void draw_pacGomme(Graphics g, int Scale) {
		pacgomme.draw_pacGomme(g, Scale);
		
	}



}

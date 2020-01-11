package PacmanModel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import PacmanModel.PacmanState.State;

public class Pacman extends Personnage {
	
	private static final int nbInstance = 1;
	private static  int compteurInstance = 0;
	private boolean isAlive;
	private Compteur counter;
	private PacmanState state; 
	private int dureEffect;
	
	private Pacman(Game game,Color colorPacman,Room entree, Direction dir){	
		super(entree, dir, game, colorPacman);
		counter = new Compteur(this);
		isAlive = true;
		state = new NormalPacman(this);
	}

	public void eat(PacGomme pac){
		pac.addScor(this);
		if(pac instanceof PacGommeActive)
		{
			System.out.println("actif");
			this.dureEffect = ((PacGommeActive) pac).getDureeEffect();
			((PacGommeActive) pac).launcheEffect();
		}
	}
	public Compteur getCompteur()
	{
		return counter;
	}

	int getDureEffect(){
		return this.dureEffect;
	}
	
	public static Pacman creerPacman(Game game, Color colorPacman, Room entree, Direction dir){
		if(compteurInstance < nbInstance){
			compteurInstance++;
			return new Pacman(game,colorPacman, entree, dir);
		}
		return null;		
	}

	@Override
	protected void notifyObserver(List<Room> events) {
		for(PersonnageObserver personnageObserver : observers)
			personnageObserver.notifyPacmanEvent(events);	
	}

	@Override
	public Room move() {
		return this.state.move();
	}
	
	public boolean isAlive() {
		return this.isAlive;
	}
	public void setIslive(boolean live){
		this.isAlive = live;
	}
	@Override
	public void die() {
		state.die();
	}
	
	@Override
	public void ChangeRoom(Room room) {
		if(room instanceof RoomMajic) {
			System.out.println("pac man majic room");
		}
		this.currentRom = room;
		this.memoryDirection = direction;
		List<Room> events = new ArrayList<>();
		events.add(this.currentRom);
		notifyObserver(events);	
	}
	@Override
	public void reflexion(){
		this.setDirection(memoryDirection);
	}
	
	
	public void changeState(PacmanState state){
		this.state = state;
	}
	public State getStatePacman(){
		return this.state.getState();
	}

	public void reset() {
		this.isAlive = true;
		this.counter.reset();
		this.currentRom = this.startRoom;
	}
	@Override
	public void draw_element(Graphics g, int Scale) {
		g.fillOval(this.currentRom.getLocation().getX() * Scale + 20,
		          currentRom.getLocation().getY() * Scale + 20, Scale / 3, Scale / 3);	
	}

	

}

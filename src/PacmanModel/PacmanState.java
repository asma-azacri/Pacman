package PacmanModel;

public abstract class PacmanState {
	public static enum State {PacmanNormal, PacmanInvisible, SuperPacman};
	
	protected Pacman pacman;
	public PacmanState(Pacman pacman){
		this.pacman = pacman;
	}
	
	abstract State getState();
	public abstract Room move();
	public abstract void die();
}

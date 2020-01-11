package PacmanModel;

public class NormalPacman extends PacmanState {

	public NormalPacman(Pacman pacman) {
		super(pacman);
	}

	@Override
	State getState() {
		return State.PacmanNormal;
	}

	@Override
	public Room move() {
		Room next = pacman.getCurrentRoom().move(pacman.getDirection());
		return next;
	}

	@Override
	public void die(){
		pacman.getCompteur().loseLife();
	}

}

package PacmanModel;

public class PacmanInvisible extends PacmanState {
	
	private int timer;
	public PacmanInvisible(Pacman pacman) {
		super(pacman);
		timer = pacman.getDureEffect();
	}

	@Override
	State getState() {
		return State.PacmanInvisible;
	}

	@Override
	public Room move() {
		Room next = this.pacman.getCurrentRoom().move(pacman.getDirection());
		if(timer > 0) timer--;
		else pacman.changeState(new NormalPacman(pacman));	
		return next;
	}

	@Override
	public void die(){
		pacman.changeState(new NormalPacman(pacman));
	}

}

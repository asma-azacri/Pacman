package PacmanModel;

public class SuperPacman extends PacmanState {

	private int timer;
	public SuperPacman(Pacman pacman) {
		super(pacman);
		this.timer = pacman.getDureEffect();
	}
	
	@Override
	State getState() {
		return State.SuperPacman;
	}

	@Override
	public Room move() {
		Room next = pacman.getCurrentRoom().move(pacman.getDirection());
		if(next.existPacGomme()) {
			pacman.eat(next.getPacGomme());
			next.getPacGomme().die();
		}
		if(timer > 0)
			timer--;
		else
			pacman.changeState(new NormalPacman(pacman));
		return next.move(pacman.getDirection());
	}
	
	@Override
	public void die() {
		for(Fantome ghost : pacman.getGame().getFantome())
		{
			if(ghost.getCurrentRoom().equals(pacman.getCurrentRoom()))
				ghost.die();
		}
	}

}

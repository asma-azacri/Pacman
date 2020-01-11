package PacmanModel;

public class SuperEffect implements Effect{
	
	private Element element;
	public SuperEffect(Element element) {
		this.element = element;
	}
	private final int DureeEffect = 30;

	@Override
	public void effect() {
		if(element instanceof Pacman){
			((Pacman) element).changeState(new SuperPacman((Pacman) element));
		}
		if(element instanceof Maze){
				((Maze) element).restructure();
		}
	}

	@Override
	public int getDuree() {
			return this.DureeEffect;

	}
	





}

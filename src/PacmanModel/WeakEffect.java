package PacmanModel;

public class WeakEffect implements Effect{

	private final int duree = 20;
	private Element element;
	public WeakEffect(Element element) {
		this.element = element;
	}
	
	
	@Override
	public void effect() {
		if(element instanceof Pacman)
			((Pacman) element).changeState(new PacmanInvisible((Pacman)element));
	}

	@Override
	public int getDuree() {
		return this.duree;
	}


}

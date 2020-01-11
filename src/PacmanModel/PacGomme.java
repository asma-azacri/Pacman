package PacmanModel;

import java.awt.Graphics;

public interface PacGomme {

	Room getRoom();
	public void die();
	public Game getGame();
	public void addScor(Pacman pacman);
	public void draw_pacGomme(Graphics g, int Scale);
	public void setRoom(Room room);
}

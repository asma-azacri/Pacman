package PacmanModel;

import java.awt.Color;
import java.awt.Graphics;

public class Door implements MapSite {

	public  enum Sens { Vertical, Horisental};
	
	private final Color color_Door;
	private boolean isOpen;
	private Sens sens;
	// les deux rooms représentent extrémités d'un chemin possible 
	private Room roomA, roomB;
	public Door(Room roomA, Room roomB, boolean isOpen){
		this.isOpen = isOpen;
		this.roomA = roomA;
		this.roomB = roomB;
		this.color_Door = Color.RED;
	}
	
	boolean IsOpen() {
		return isOpen;
	}
	
	/**
	 *  
	 * @param sens  de la porte d'une pièce
	 */
	public void setSens(Sens sens){
		this.sens = sens;
	}
	

   /**
    * 
    * @param room
    * @return l'autre extrémité de chemin selon le paramétre de la méthode 
    */
	public Room chemin(Room room) {
		if(room.equals(roomA))
			return this.roomB;
		else if(room.equals(roomB))
			return this.roomA;
		else
			return null;
	}
	
	

	
	@Override
	public void draw(Graphics g, int x, int y, int w, int h) {
		g.setColor(Color.black);
		g.setColor(color_Door);
		if(sens.equals(Sens.Vertical)){
			 int a = x;
			 int b = w;
			 a += 4 * h; b -= 4 * h; 
			g.fill3DRect(a, y, b, h, true);
			x += 1 * h; w -=4 *h;
			g.fill3DRect(x, y, w, h, true);
		} else { 
			int a = y;
			int b = h;
			a += 1 * w; b -= 4 * w; 
			
			g.fill3DRect(x, a, w, b, true);
			y += 4 * w; h -= 4 * w;
			g.fill3DRect(x, y, w, h, true);
		}
	}








}

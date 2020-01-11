package PacmanModel;

import java.awt.Color;
import java.awt.Graphics;

public class Wall implements MapSite{

	private Color color_Wall;
	public  Wall(){
		this.color_Wall = Color.GRAY;
	}

	
	public void changeColor(Color colorWall) {
		this.color_Wall = colorWall;
	}


	@Override
	public void draw(Graphics g, int x, int y, int w, int h) {
		g.setColor(color_Wall);
		g.fill3DRect(x, y, w, h, true);
	}

	



}

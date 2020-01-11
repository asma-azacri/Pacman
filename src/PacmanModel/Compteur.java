package PacmanModel;

import java.awt.Color;
import java.awt.Graphics;

public class Compteur {
	private int score;
	private int nbr_Vie;
	private int paraX_Score = 20;
	private int paraY_Score = 440;
	private int paraX_nbrVie = 310;
	private int paraY_nbrVie = 440;
	private Color color_Score = Color.ORANGE;
	private Color color_nbrVie = Color.ORANGE;
	private int tmp ;
	private int predScore;
	private Pacman pacman;
	public Compteur(Pacman pacman){
		this.pacman = pacman;
		this.score = 0;
		this.nbr_Vie  = 3;
		this.tmp = 0;
		this.predScore = 0;
		
	}
	
	public void upDateScore(int points){   
		this.score += points;
		tmp = score - predScore;
		  System.out.println("compteur tmp :"+tmp);
		int s = tmp % 5000;
		int d = tmp / 5000;
		if((d >= 1) && (s == 0 || s == 100 || s == 200  || s == 300  || s == 400)){  
			    this.predScore  = score - points;
			   
				if(tmp >= 5000 && this.nbr_Vie < 3)
					this.nbr_Vie++; 
				 tmp = 0;
		}		
	}
	public void reset() {
		this.resetNbrVie();
		this.resetScore();
	}
	private void resetNbrVie() {
		this.nbr_Vie = 3;
	}
	
	private void resetScore() {
		this.score = 0;
	}
	
	
	public void loseLife(){	
		this.nbr_Vie--;
		if(this.nbr_Vie == 0)
			pacman.setIslive(false);
	}


	public void draw_Score(Graphics g){
		g.setColor(color_Score);	
		g.drawString("Score : "+this.score, this.paraX_Score, this.paraY_Score);  
	}
	public void draw_nbrVie(Graphics g){
		 g.setColor(color_nbrVie);	
		 g.drawString("Vie : "+ this.nbr_Vie+ " "+ "", this.paraX_nbrVie, this.paraY_nbrVie);
	}

}


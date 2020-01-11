package PacmanModel;

import java.awt.Color;

public class FactoryPerosonnage {
	
	public static enum TypePersonnage { Hero, Ennemie } 
	public static Personnage makePersonnage(TypePersonnage typePersonnage,Game game, Color color,Room entrer, Direction dir)   
	{
		if(typePersonnage.equals(TypePersonnage.Hero))
			return Pacman.creerPacman(game,color, entrer, dir);
		else if(typePersonnage.equals(TypePersonnage.Ennemie))
			return Fantome.creerFantome(game, color, entrer, dir);
		else
			return null;
	}
}

package PacmanModel;

import java.awt.Color;
import java.util.ArrayList;
import PacmanModel.FactoryPerosonnage.TypePersonnage;


public class Game {
	
	private Maze maze;	
	private Pacman pacman;
	private ArrayList<Fantome> fantomes = new ArrayList<>();;
	private MazeGame m;
	
	public Game(int idRoom){
			m = new MazeGame(this);
			maze = m.createMaze();
			pacman  =   (Pacman) FactoryPerosonnage.makePersonnage(TypePersonnage.Hero,this, Color.YELLOW, maze.findRoom(idRoom), Direction.Est);
			fantomes.add((Fantome) FactoryPerosonnage.makePersonnage(TypePersonnage.Ennemie,this, Color.magenta, maze.findRoom(1), Direction.Sud));
			fantomes.add((Fantome) FactoryPerosonnage.makePersonnage(TypePersonnage.Ennemie,this, Color.CYAN,maze.findRoom(9), Direction.Nord));
			fantomes.add((Fantome) FactoryPerosonnage.makePersonnage(TypePersonnage.Ennemie,this, Color.WHITE,maze.findRoom(25), Direction.Est));
			fantomes.add((Fantome) FactoryPerosonnage.makePersonnage(TypePersonnage.Ennemie,this, Color.LIGHT_GRAY,maze.findRoom(4), Direction.Ouest));
			maze.setPacman(pacman);
			for(Fantome ghost : fantomes)
				maze.setFantome(ghost);
			maze.fillMaze();	
	}

	/**
	 * condition d'arrêt de jeu
	 * @return boolean 
	 */
	public boolean IsGameOver(){
	 return pacman.isAlive() && !(maze.getlistPac().isEmpty());
	}
	
	public void step(){
		
		maze.step();
		
		if(pacman.getCurrentRoom().existPacGomme()){
			PacGomme pac = pacman.getCurrentRoom().getPacGomme();
			pacman.eat(pac);
			pac.die();
		}		
		for(Fantome ghost : fantomes) {
			if(ghost.currentRom.equals(pacman.currentRom))
				pacman.die();
		}		
			
		
	}
	
	public void setMaze(Maze maze ){
		this.maze = maze ;
	}
	public Maze getMaze(){
		return this.maze;
	}
	public MazeGame getMazeGame(){
		return m;
	}
	
	public Pacman getPacman(){
	
		return (Pacman)pacman;
	}
	public ArrayList<Fantome> getFantome(){
		return fantomes;
	}
	
	public void reset() {
		maze.fillMaze();
		pacman.reset();
	}

}

package PacmanGui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import PacmanModel.Compteur;
import PacmanModel.Direction;
import PacmanModel.Fantome;
import PacmanModel.Game;
import PacmanModel.Maze;
import PacmanModel.PacGomme;
import PacmanModel.Pacman;
import PacmanModel.PacmanState;
import PacmanModel.PacmanState.State;
import PacmanModel.PersonnageObserver;
import PacmanModel.Room;


public class Gui implements PersonnageObserver {
	
	private JFrame frame = new JFrame("Pac-Man");
	private Game game;
	private Maze maze;
	private Pacman pacman;
	private ArrayList<Fantome> fantomes;
	private ArrayList<PacGomme> pacgommes;
	private Compteur compteur;
	private final int Scale = 50;
	private static Map<State, Color> colorMap = new HashMap<PacmanState.State, Color>();	
	static {
		colorMap.put(State.SuperPacman, Color.ORANGE);
		colorMap.put(State.PacmanInvisible,  new Color(219,112,147));   // color
		colorMap.put(State.PacmanNormal, Color.YELLOW);
	}
	
	private JComponent component = new JComponent() {
		
		private static final long serialVersionUID = 4237746179597757563L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			maze.draw_element(g, Scale);
			for(PacGomme pac_Gomme : pacgommes){
				pac_Gomme.draw_pacGomme(g, Scale);
				
			}	
		
			g.setColor(colorMap.get(pacman.getStatePacman()));
			pacman.draw_element(g, Scale);
			
			compteur.draw_nbrVie(g);
			compteur.draw_Score(g);
			
			for(Fantome ghost : fantomes){
				if(pacman.getStatePacman().equals(State.SuperPacman))
					ghost.setColor(Color.BLUE);
				else
					ghost.resetColor();
				ghost.draw_element(g, Scale);
			}		
		}			
	};

	public Gui(Game game){
		this.game = game;
		this.maze = game.getMaze();
		this.pacman = game.getPacman();
		this.compteur = pacman.getCompteur();
		this.fantomes = game.getFantome();
		this.pacgommes = game.getMaze().getlistPac();


		frame.setContentPane(component);
		frame.setSize(410,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setBackground(Color.GRAY);
		frame.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					pacman.setDirection(Direction.Ouest);
					break;
				case KeyEvent.VK_RIGHT:
					pacman.setDirection(Direction.Est);
					break;
				case KeyEvent.VK_UP:
					pacman.setDirection(Direction.Nord);
					break;
				case KeyEvent.VK_DOWN:
					pacman.setDirection(Direction.Sud);
					break;
				default:
					break;
				}
			}
			
		});
		
			
	}
	

	
	/**
	 * méthode affiche un message dans le cas où le joueur a perdu le jeu ou a gagné et  proposer à lui si il veut rejouer  
	 * @return
	 */
	public int messageGameOver() {
		if(!pacman.isAlive())
			return JOptionPane.showConfirmDialog(this.frame, "Game Over \n Vous Rejouez ?", 
				  "Pacman Game", JOptionPane.OK_OPTION);
		else
			return JOptionPane.showConfirmDialog(this.frame, "Félicitation : Vous avez Gagné le jeu \n Vous Rejouez ?", 
					"Pacman Game", JOptionPane.OK_OPTION);
	}
	@Override
	public void notifyPacmanEvent(List<Room> events){	
		component.repaint();
	}

	@Override
	public void notifyFantomeEvent(List<Room> events){
		
		component.repaint();		
	}
	
	public Game getGame()
	{
		return this.game;
	}

}

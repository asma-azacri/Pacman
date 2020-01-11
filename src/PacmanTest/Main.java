package PacmanTest;

import javax.swing.JOptionPane;

import PacmanGui.Gui;
import PacmanModel.Game;
import PacmanModel.Personnage;

public class Main {
	
	public static boolean arretGame(boolean arret ) {
		return arret;
	}
	public static void main(String[] args){
		
		int start_id_Room = 63;		
		final int  SPEED = 250;
		Game game = new Game(start_id_Room);
		Gui gui = new Gui(game);
		game.getPacman().register(gui);
		for(Personnage p : game.getFantome())
			p.register(gui);
		// Boucle de jeu
		while(!arretGame(false)) {
			while(game.IsGameOver()){
		    	game.step();
		    		try {
		    			Thread.sleep(SPEED);
		    		} catch (InterruptedException e) {
		    			System.out.println(e.getMessage());
		    		}
		    }
			int retour = gui.messageGameOver();
			if(retour == JOptionPane.OK_OPTION) {
				game.reset();
			}
			else {
				arretGame(true);
				System.exit(0);
			}		
	    }
		
	}
}
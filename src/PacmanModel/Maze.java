package PacmanModel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import  java.util.Iterator;


public class Maze implements Element{
	
	private Game game;
	private ArrayList<Room> listRoom;
	private ArrayList<PacGomme> listPacGomme;
	private Personnage pacman;
	private ArrayList<Personnage> fantomes;

	public Maze(Game game) {		
		this.game = game;
		listRoom = new ArrayList<Room>();
		fantomes = new ArrayList<>();
		listPacGomme = new ArrayList<>();		
	}
	
	public void fillMaze() {
		
		Room room1 = listRoom.get(60);
		Room room2 = listRoom.get(7);
		Room room3 = listRoom.get(15);
		Room room4 = listRoom.get(24);
		Room room5 = listRoom.get(40);
		
		PacGommeActive pac1 = FactoryMaze.makePacGommeActive(game, room1, Color.GREEN, 1000, new SuperEffect(this));
		room1.setPacgomme(pac1);
		PacGommeActive pac2 = FactoryMaze.makePacGommeActive(game, room2, Color.ORANGE, 500, new SuperEffect(pacman));
		room2.setPacgomme(pac2);
		PacGommeActive pac3 = FactoryMaze.makePacGommeActive(game, room3, Color.ORANGE, 300, new WeakEffect(pacman));
		room3.setPacgomme(pac3);
		PacGommeActive pac4 = FactoryMaze.makePacGommeActive(game, room4, Color.ORANGE, 500, new SuperEffect(pacman));
		room4.setPacgomme(pac4);
		PacGommeActive pac5 = FactoryMaze.makePacGommeActive(game, room5, Color.ORANGE, 300, new WeakEffect(pacman));
		room5.setPacgomme(pac5);

		for(Room r : listRoom) {
			if(!r.existPacGomme()){
				SimplePacGomme pac = (SimplePacGomme) FactoryMaze.makeSimplePacGomme(game, r, Color.BLUE, 100);
				r.setPacgomme(pac);
			}
		}
	}
	public Maze restructure() {
		for(Room r : listRoom) {
			r.resetRoom();
		}
		return game.getMazeGame().generate();
	}
	// Room
	public ArrayList<Room> getListRoom(){
		return this.listRoom;
	}
	public void addRoom(Room room){
		this.listRoom.add(room);
	}
	public Room findRoom(int id) {
		if(id < listRoom.size()) {
			Iterator<Room> iter = listRoom.iterator(); 
			while(iter.hasNext()){
			Room r = iter.next();
			if(r.getRoomId() == id)
				return r;
			}
		}
		throw new  IllegalArgumentException("Le Id de la pièce n'existe pas");
	}
	// Pac-Gomme
	public void setPacGomme(PacGomme pac){
		this.listPacGomme.add(pac);
	}
	public void removePacGomme(PacGomme pac){
		if(listPacGomme.contains(pac)){
				listPacGomme.remove(pac);
		}
	}	
	public  ArrayList<PacGomme> getlistPac(){
		return listPacGomme;
	}
	public boolean existPacgommes(){
		return this.listPacGomme.isEmpty();
	}
	
    // Personnage
	public void setPacman(Pacman pacman){
		this.pacman = pacman;
	}
	public void setFantome(Fantome fantome){
		this.fantomes.add(fantome);
	}
	public Pacman getPacman() {
		return (Pacman)this.pacman;
	}
	
	public  ArrayList<Personnage> getFantomes(){
		return this.fantomes;
	}
	
	/**
	 * deplacement des personnages sur le labyrinthe
	 */
	public void step(){
		pacman.move().entrer(pacman);
		for( Personnage ghost :fantomes)
			ghost.move().entrer(ghost);
	}
    
	
	/**
	 *  Dessin du labyrinthe 
	 */
	@Override
	public void draw_element(Graphics g, int Scale){
		//Dessin d'une pièce 
		for(Room room : listRoom){
			Coordinate location  = room.getLocation();
			room.draw(g, location.getX() * Scale, location.getY() * Scale, Scale, Scale);
		}
		// Dessin des cotés d'une pièces
		for(Room room : listRoom){
			Coordinate location = room.getLocation();
				room.getSite(Direction.Nord).draw(g, location.getX() * Scale, (location.getY()) * Scale, Scale , Scale  / 5);	
				room.getSite(Direction.Est).draw(g,(location.getX() + 1) * Scale, location.getY() * Scale, Scale  / 5, Scale );
				room.getSite(Direction.Sud).draw(g,location.getX() * Scale, (location.getY() + 1) * Scale , Scale , Scale  / 5);
				room.getSite(Direction.Ouest).draw(g,((location.getX()) * Scale), location.getY() * Scale, Scale  / 5, Scale  );			
		}
	}

	public Game getGame(){
		return game;
	}

}

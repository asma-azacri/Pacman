package PacmanModel;

import java.awt.Color;

public class RoomMajic extends Room {
	
	private Room targetRoom;
	public RoomMajic(Maze maze, Coordinate location) {
		super(maze, location,Color.MAGENTA);
		
		
	}
	
	/**
	 * 
	 * @return le numéro (id) d'une pièce destination
	 */
	private int destination() {
		if( this.getRoomId() == 7) {
			return 56;	
		}
		else if(this.getRoomId() == 48 )
			return 5;
		return 0;
	}
	@Override
	public void entrer(Personnage personnage)  {
		targetRoom = this.getMaze().getListRoom().get(destination());
		personnage.ChangeRoom(targetRoom);
	}



}

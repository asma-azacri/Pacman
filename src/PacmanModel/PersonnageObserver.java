package PacmanModel;

import java.util.List;


public interface PersonnageObserver {
	public void notifyPacmanEvent(List<Room> events) ;
	public void notifyFantomeEvent(List<Room> events) ;

}

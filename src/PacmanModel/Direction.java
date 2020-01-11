package PacmanModel;

public enum Direction {
	Nord(0,-1), Sud(0,1), Est(1,0), Ouest(-1,0);
	
	Direction(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	private  int x;
	private int y;
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
}

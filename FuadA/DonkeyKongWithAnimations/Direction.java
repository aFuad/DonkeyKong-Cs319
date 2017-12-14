package source;

public enum Direction {
	LEFT(0),
	RIGHT(1);
	
	private int direction;
	
	Direction(int direction){
		this.direction = direction;
	}
	
	public int getDirectionValue(){
		return direction;
	}
}

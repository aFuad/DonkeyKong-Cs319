package source;

public enum Direction {
	RIGHT(0),
	LEFT(1);
	
	private int value;
	
	Direction(int value){
		this.value = value;
	}
	
	public int getDirectionValue(){
		return value;
	}
}

package source;

public enum EnemyType {
	ROLLING_BARREL(0),
	FALLING_BARREL(1),
	FIRE_ELEMENTAL(2);
	
	private int value;
	
	EnemyType(int value){
		this.value = value;
	}
	
	public int getDirectionValue(){
		return value;
	}
}
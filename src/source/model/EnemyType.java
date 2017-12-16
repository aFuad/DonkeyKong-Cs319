package source.model;

public enum EnemyType {
	ROLLING_BARREL(0),
	FALLING_BARREL(1),
	FIRE_ELEMENTAL(2);
	
	private int type;
	
	EnemyType(int type){
		this.type = type;
	}
	
	public int getTypeValue(){
		return type;
	}
}
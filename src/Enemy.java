package source;

public abstract class Enemy extends MyObject implements Movable{

	private Direction direction = Direction.RIGHT;
	private EnemyType type;
	
	public Enemy(int x, int y){
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public void setEnemyType(EnemyType type){
		this.type = type;
	}
	
	public EnemyType getEnemyType(){
		return type;
	}
	
	public void setX(int x){
		super.setX(x);
	}
	
	public void setY(int y){
		super.setY(y);
	}
	
	public void setDirection(Direction direction){
		this.direction = direction;
	}
	
	public Direction getDirection(){
		return direction;
	}
	
	public void goRight(){
		setX(getX() + 1);
	}
	
	public void goLeft(){
		setX(getX() - 1);
	}
}
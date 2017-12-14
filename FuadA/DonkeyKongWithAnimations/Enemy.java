package source;

public abstract class Enemy extends MyObject implements Movable{

	private Direction direction;
	private EnemyType enemyType;
	
	//Checks whether or not player jumped from this particular object. In other word, prevent player to farm points and make the coder's jub easier
	private boolean scorable;
	
	public Enemy(int x, int y){
		super(x, y);
		direction = Direction.RIGHT;
		setScorable(true);
		// TODO Auto-generated constructor stub
	}

	public void setEnemyType(EnemyType enemyType){
		this.enemyType = enemyType;
	}
	
	public EnemyType getEnemyType(){
		return enemyType;
	}
	
	public void setDirection(Direction direction){
		this.direction = direction;
	}
	
	public Direction getDirection(){
		return direction;
	}
	
	public void goRight(){
		setX(getX() + 5);
		relocateRectangle(getX(), getY());
	}
	
	public void goLeft(){
		setX(getX() - 5);
		relocateRectangle(getX(), getY());
	}

	public boolean isScorable() {
		return scorable;
	}

	public void setScorable(boolean scorable) {
		this.scorable = scorable;
	}
}

public class Enemy extends MyObjects implements Movable{
	
	Direction main = Direction.E_Right;
	enemyType enm = enemyType.FallingBarrel;

	public void setEnemyType(enemyType a){
		enm=a;
	}
	
	public enemyType getEnemyType(){

		return enm;
	}
	
	public int getX(){
		return super.getX();
	}
	public int getY(){
		return super.getY();
	}
	public void setX(int a){
		super.setX(a);
	}
	
	public void setY(int a){
		super.setY(a);
	}
	
	public void goRight(){
		setX(getX()+1);
	}
	public void goLeft(){
		setX(getX()-1);
	}
	public void operation(){
		//waiting for comment
	}
	public void setDirection(Direction a){
		main = a;
	}
	public Direction getDirection(){
		return main;
	}
}

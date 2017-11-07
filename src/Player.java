package source;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Player extends MyObject implements Movable{
	private Image imagePlayerRight = new ImageIcon(this.getClass().getResource("right.png")).getImage();
	private Image imagePlayerLeft = new ImageIcon(getClass().getResource("left.png")).getImage();
	private Image imagePlayerStrike = new ImageIcon(getClass().getResource("strike.png")).getImage();
	private Image imageJumpRight = new ImageIcon(getClass().getResource("jumpRight.png")).getImage();
	private Image imageJumpLeft = new ImageIcon(getClass().getResource("jumpLeft.png")).getImage();
	private Image imagePlayerClimb1= new ImageIcon(getClass().getResource("climb1.png")).getImage();
	private Image imagePlayerClimb2 = new ImageIcon(getClass().getResource("climb2.png")).getImage();
	
	private Direction direction;

	public Player(int x, int y){
		super(x, y);
		direction = Direction.RIGHT;
	}
	
	public Image getImagePlayerRight(){
		return imagePlayerRight;
	}
	
	public Image getImagePlayerLeft(){
		return imagePlayerLeft;
	}
	
	public Image getImagePlayerStrike(){
		return imagePlayerStrike;
	}
	
	public Image getImageJumpRight(){
		return imageJumpRight;
	}
	
	public Image getImageJumpLeft(){
		return imageJumpLeft;
	}
	
	public Image getImagePlayerClimb1(){
		return imagePlayerClimb1;
	}
	
	public Image getImagePlayerClimb2(){
		return imagePlayerClimb2;
	}

	public void goUp(){
		setY(getY() + 1);
	}
	
	public void goDown(){
		setY(getY() - 1);
	}
	
	//We cannot write a jump function in GameEngine Class or GamePanel Class
	
	public void goRight(){
		setX(getX()+1);
	}
	
	public void goLeft(){
		setX(getX()-1);
	}
	
	public void setDirection(Direction direction){
		this.direction = direction;
	}
	
	public Direction getDirection(){
		return direction;
	}
}
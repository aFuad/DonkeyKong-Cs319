package source;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Player extends MyObject implements Movable{
	private Image imagePlayerRight = new ImageIcon("src/image/player.png").getImage();
	private Image imagePlayerLeft = new ImageIcon("src/image/player.png").getImage();
	private Image imagePlayerStrike = new ImageIcon("src/image/player.png").getImage();
	private Image imageJumpRight = new ImageIcon("src/image/player.png").getImage();
	private Image imageJumpLeft = new ImageIcon("src/image/player.png").getImage();
	private Image imagePlayerClimb1= new ImageIcon("src/image/player.png").getImage();
	private Image imagePlayerClimb2 = new ImageIcon("src/image/player.png").getImage();
	
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

	/*
	 * Yes, that's right! Go up decreasing horizontal component of the player.
	 * Similarly Go down do same thing but reverse.
	 */
	public void goUp(){
		setY(getY() - 5);
		relocateRectangle(getX(), getY());
	}
	
	public void goDown(){
		setY(getY() + 5);
		relocateRectangle(getX(), getY());
	}
	
	//We cannot write a jump function in GameEngine Class or GamePanel Class
	
	public void goRight(){
		setX(getX() + 5);
		relocateRectangle(getX(), getY());
	}
	
	public void goLeft(){
		setX(getX() - 5);
		relocateRectangle(getX(), getY());
	}
	
	public void setDirection(Direction direction){
		this.direction = direction;
	}
	
	public Direction getDirection(){
		return direction;
	}
	
	public void falling(){
		setY(getY() + 10);
		relocateRectangle(getX(), getY());
	}
}

package source;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Player extends MyObject implements Movable{
	private Image imageStandLeft = new ImageIcon("src/image/1.png").getImage();
	private Image imageWalkLeft = new ImageIcon("src/image/2.png").getImage();
	private Image imageStandRight = new ImageIcon("src/image/1right.png").getImage();
	private Image imageWalkRight = new ImageIcon("src/image/2right.png").getImage();
	private Image imageClimb1 = new ImageIcon("src/image/3.png").getImage();
	private Image imageClimb4 = new ImageIcon("src/image/3right.png").getImage();
	private Image imageClimb2 = new ImageIcon("src/image/4.png").getImage();
	private Image imageClimb3 = new ImageIcon("src/image/5.png").getImage();
	private Image imageStandBack = new ImageIcon("src/image/6.png").getImage();
	private Image imageStandWithHammerLeft = new ImageIcon("src/image/7.png").getImage();
	private Image imageStandWithHammerRight = new ImageIcon("src/image/7right.png").getImage();
	private Image imageStandStrikeLeft = new ImageIcon("src/image/8.png").getImage();
	private Image imageStandStrikeRight = new ImageIcon("src/image/8right.png").getImage();
	private Image imageWalkWithHammer1Left = new ImageIcon("src/image/9.png").getImage();
	private Image imageWalkWithHammer1Right = new ImageIcon("src/image/9right.png").getImage();
	private Image imageWalkStrike1Left = new ImageIcon("src/image/10.png").getImage();
	private Image imageWalkStrike1Right = new ImageIcon("src/image/10right.png").getImage();
	private Image imageWalkWithHammer2Left = new ImageIcon("src/image/11.png").getImage();
	private Image imageWalkWithHammer2Right = new ImageIcon("src/image/11right.png").getImage();
	private Image imageWalkStrike2Left = new ImageIcon("src/image/12.png").getImage();
	private Image imageWalkStrike2Right = new ImageIcon("src/image/12right.png").getImage();
	private Image imageJumpLeft = new ImageIcon("src/image/13.png").getImage();
	private Image imageJumpRight = new ImageIcon("src/image/13right.png").getImage();
	private Image imageSomething = new ImageIcon("src/image/14.png").getImage(); //I am not sure what this image is
	private Image imageDead1 = new ImageIcon("src/image/15.png").getImage();
	private Image imageDead2 = new ImageIcon("src/image/16.png").getImage();
	private Image imageDead3 = new ImageIcon("src/image/17.png").getImage();
	private Image img = imageStandLeft;
	
	private boolean jump;
	private boolean climb;
	private boolean hammer;
	private boolean climbEnd;
	private Direction direction;

	
	public Player(int x, int y){
		super(x, y);
		direction = Direction.LEFT;
		jump=false;
		climb=false;
		hammer=false;
		climbEnd=false;
	}

	/*
	 * Yes, that's right! Go up decreasing horizontal component of the player.
	 * Similarly Go down do same thing but reverse.
	 */
	public void goUp(){
		setY(getY() - 5);
		relocateRectangle(getX(), getY());
		System.out.println("------"+getY());
	}
	
	public void goDown(){
		setY(getY() + 5);
		relocateRectangle(getX(), getY());
		System.out.println("------"+getY());
	}
	
	//We cannot write a jump function in GameEngine Class or GamePanel Class
	
	public void goRight(){
		setX(getX() + 5);
		relocateRectangle(getX(), getY());
		setDirection(Direction.RIGHT);
	}
	
	public void goLeft(){
		setX(getX() - 5);
		relocateRectangle(getX(), getY());
		setDirection(Direction.LEFT);
	}
	
	public void setDirection(Direction direction){
		this.direction = direction;
	}
	
	public Direction getDirection(){
		return direction;
	}
	
	public boolean getJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}
	
	public boolean getClimb() {
		return climb;
	}

	public void setClimb(boolean climb) {
		this.climb = climb;
	}
	
	public void setClimbEnd(boolean climbEnd){
		this.climbEnd=climbEnd;
	}
	
	public boolean getClimbEnd(){
		return climbEnd;
	}
	
	public boolean getHammer() {
		return hammer;
	}

	public void setHammer(boolean hammer) {
		this.hammer = hammer;
	}
	
	public void animationJump(){
		if(jump==true){
			if(getDirection()==Direction.LEFT){
				if(getY()%10<10)img=imageJumpLeft;
			}
			else 
				if(getY()%10<10)img=imageJumpRight;
		}
	}
	
	public void animationx(){
		if(getDirection()==Direction.LEFT){
			if(getX()%60 >29) img=imageStandLeft;
			else if(getX()%60 <30) img=imageWalkLeft;
		}
		else if(getDirection()==Direction.RIGHT){
			if(getX()%60 >29) img=imageStandRight;
			else if(getX()%60 <30) img=imageWalkRight;
		}
	}
	
	public void animationClimb(){
		if(getClimb()){
			if(jump==false){
				if(getY()%60>29) img = imageClimb1;
				if(getY()%60<30) img = imageClimb4;
			}
		}
	}
	
	public void animationClimbEnd(){
		System.out.println(getClimbEnd());
		if(getClimbEnd()){
			int a=0;
			if(getY()%20<10) img=imageClimb2;
			else if(getY()%20>9) img=imageClimb3;
		}
	}
	
	public void animationFixer(){
		if(getDirection()==Direction.LEFT)img=imageStandLeft;
		else img=imageStandRight;
	}
	
	public void animationHammer(){
		if(getHammer()){
				if(getDirection()==Direction.LEFT){
					if(getX()%120 <20) img=imageStandWithHammerLeft;
					else if(getX()%120 >20 && getX()%120 <40) img=imageStandStrikeLeft;
					else if(getX()%120 >40 && getX()%120 <60) img=imageWalkWithHammer1Left;
					else if(getX()%120 >60 && getX()%120 <80) img=imageWalkStrike1Left;
					else if(getX()%120 >80 && getX()%120 <100) img=imageWalkWithHammer2Left;
					else if(getX()%120 >100) img=imageWalkStrike2Left;
			}
			else if(getDirection()==Direction.RIGHT){
				if(getDirection()==Direction.LEFT){
					if(getX()%120 <20) img=imageStandWithHammerRight;
					else if(getX()%120 >20 && getX()%120 <40) img=imageStandStrikeRight;
					else if(getX()%120 >40 && getX()%120 <60) img=imageWalkWithHammer1Right;
					else if(getX()%120 >60 && getX()%120 <80) img=imageWalkStrike1Right;
					else if(getX()%120 >80 && getX()%120 <100) img=imageWalkWithHammer2Right;
					else if(getX()%120 >100) img=imageWalkStrike2Right;
				}
			}	
		}
		else{
			
			
			
			
		}
		
	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		animationx();
		if(getClimbEnd()==false){
			animationClimb();
		}
		else{
			animationClimbEnd();
			animationFixer();
		}
		animationJump();
		
		

		return img;
	}

	

	

	
}
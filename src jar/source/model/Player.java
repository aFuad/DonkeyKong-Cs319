package source.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Player extends MyObject implements Movable{
	private BufferedImage bufferedImage1;
	private BufferedImage bufferedImage2;
	private BufferedImage bufferedImage3;
	private BufferedImage bufferedImage4;
	private BufferedImage bufferedImage5;
	private BufferedImage bufferedImage6;
	private BufferedImage bufferedImage7;
	private BufferedImage bufferedImage8;
	private BufferedImage bufferedImage9;
	private BufferedImage bufferedImage10;
	private BufferedImage bufferedImage11;
	private BufferedImage bufferedImage12;
	private BufferedImage bufferedImage13;
	private BufferedImage bufferedImage14;
	private BufferedImage bufferedImage15;
	private BufferedImage bufferedImage16;
	private BufferedImage bufferedImage17;
	private BufferedImage bufferedImage18;
	private BufferedImage bufferedImage19;
	private BufferedImage bufferedImage20;
	private BufferedImage bufferedImage21;
	private BufferedImage bufferedImage22;
	private Image imageStandLeft;
	private Image imageWalkLeft;
	private Image imageStandRight;
	private Image imageWalkRight;
	private Image imageClimb1;
	private Image imageClimb1Hammer;
	private Image imageClimb4;
	private Image imageClimb4Hammer;
	private Image imageClimb2;
	private Image imageClimb3;
	private Image imageStandWithHammerLeft;
	private Image imageStandWithHammerRight;
	private Image imageStandStrikeLeft;
	private Image imageStandStrikeRight;
	private Image imageWalkWithHammer1Left;
	private Image imageWalkWithHammer1Right;
	private Image imageWalkStrike1Left;
	private Image imageWalkStrike1Right;
	private Image imageJumpLeft;
	private Image imageJumpHammer;
	private Image imageJumpRight;
	private Image imageJumpRightHammer;
	private Image img;
	
	private boolean jump;
	private boolean climb;
	private boolean hammer;
	private boolean climbEnd;
	private Direction direction;
	
	private boolean strike;

	
	public Player(int x, int y){
		super(x, y);
		direction = Direction.LEFT;
		
		jump=false;
		climb=false;
		hammer=false;
		climbEnd=false;
		
		setStrike(false);
		
		try {
			bufferedImage1 = ImageIO.read(getClass().getResource("/image/1.png"));
			bufferedImage2 = ImageIO.read(getClass().getResource("/image/2.png"));
			bufferedImage3 = ImageIO.read(getClass().getResource("/image/1right.png"));			
			bufferedImage4 = ImageIO.read(getClass().getResource("/image/2right.png"));			
			bufferedImage5 = ImageIO.read(getClass().getResource("/image/3.png"));			
			bufferedImage6 = ImageIO.read(getClass().getResource("/image/3hammer.png"));
			bufferedImage7 = ImageIO.read(getClass().getResource("/image/3right.png"));
			bufferedImage8 = ImageIO.read(getClass().getResource("/image/3righthammer.png"));
			bufferedImage9 = ImageIO.read(getClass().getResource("/image/4.png"));			
			bufferedImage10 = ImageIO.read(getClass().getResource("/image/5.png"));			
			bufferedImage11 = ImageIO.read(getClass().getResource("/image/7.png"));			
			bufferedImage12 = ImageIO.read(getClass().getResource("/image/7right.png"));
			bufferedImage13 = ImageIO.read(getClass().getResource("/image/8.png"));
			bufferedImage14 = ImageIO.read(getClass().getResource("/image/8right.png"));
			bufferedImage15 = ImageIO.read(getClass().getResource("/image/9.png"));			
			bufferedImage16 = ImageIO.read(getClass().getResource("/image/9right.png"));			
			bufferedImage17 = ImageIO.read(getClass().getResource("/image/10.png"));			
			bufferedImage18 = ImageIO.read(getClass().getResource("/image/10right.png"));
			bufferedImage19 = ImageIO.read(getClass().getResource("/image/13.png"));
			bufferedImage20 = ImageIO.read(getClass().getResource("/image/13hammer.png"));
			bufferedImage21 = ImageIO.read(getClass().getResource("/image/13right.png"));			
			bufferedImage22 = ImageIO.read(getClass().getResource("/image/13righthammer.png"));			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageStandLeft =  new ImageIcon(bufferedImage1).getImage();
		imageWalkLeft =  new ImageIcon(bufferedImage2).getImage();
		imageStandRight =  new ImageIcon(bufferedImage3).getImage();
		imageWalkRight =  new ImageIcon(bufferedImage4).getImage();
		imageClimb1 =  new ImageIcon(bufferedImage5).getImage();
		imageClimb1Hammer =  new ImageIcon(bufferedImage6).getImage();
		imageClimb4 =  new ImageIcon(bufferedImage7).getImage();
		imageClimb4Hammer =  new ImageIcon(bufferedImage8).getImage();
		imageClimb2 =  new ImageIcon(bufferedImage9).getImage();
		imageClimb3 =  new ImageIcon(bufferedImage10).getImage();
		imageStandWithHammerLeft =  new ImageIcon(bufferedImage11).getImage();
		imageStandWithHammerRight =  new ImageIcon(bufferedImage12).getImage();
		imageStandStrikeLeft =  new ImageIcon(bufferedImage13).getImage();
		imageStandStrikeRight =  new ImageIcon(bufferedImage14).getImage();
		imageWalkWithHammer1Left =  new ImageIcon(bufferedImage15).getImage();
		imageWalkWithHammer1Right =  new ImageIcon(bufferedImage16).getImage();
		imageWalkStrike1Left =  new ImageIcon(bufferedImage17).getImage();
		imageWalkStrike1Right =  new ImageIcon(bufferedImage18).getImage();
		imageJumpLeft =  new ImageIcon(bufferedImage19).getImage();
		imageJumpHammer =  new ImageIcon(bufferedImage20).getImage();
		imageJumpRight =  new ImageIcon(bufferedImage21).getImage();
		imageJumpRightHammer =  new ImageIcon(bufferedImage22).getImage();
		
		img = imageStandLeft;
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
	
	public int getHammerY(){
		return getY() - 50;
	}
	
	public int getHammerX(){
		return getX() - 50;
	}

	public void setHammer(boolean hammer) {
		this.hammer = hammer;
	}
	
	//Need new images
	public void animationJump(){
		if(getJump()){
			if(getDirection()==Direction.LEFT){
				if(getHammer()){
					if(getY()%10<10)img=imageJumpHammer;
				}
				else{
					if(getY()%10<10)img=imageJumpLeft;
				}
			}
			else {
				if(getHammer()){
					if(getY()%10<10)img=imageJumpRightHammer;
				}
				else{
					if(getY()%10<10)img=imageJumpRight;
				}
			}
		}
	}
	
	//Need new images
	public void animationx(){
		if(getDirection()==Direction.LEFT){
			if(getHammer()){
				if(isStrike()){
					if(getX()%60 >29) img=imageStandStrikeLeft;
					else if(getX()%60 <30) img=imageWalkStrike1Left;
				}
				else{
					if(getX()%60 >29) img=imageStandWithHammerLeft;
					else if(getX()%60 <30) img=imageWalkWithHammer1Left;
				}
			}
			else{
				if(getX()%60 >29) img=imageStandLeft;
				else if(getX()%60 <30) img=imageWalkLeft;
			}
		}
		else if(getDirection()==Direction.RIGHT){
			if(getHammer()){
				if(isStrike()){
					if(getX()%60 >29) img=imageStandStrikeRight;
					else if(getX()%60 <30) img=imageWalkStrike1Right;
				}
				else{
					if(getX()%60 >29) img=imageStandWithHammerRight;
					else if(getX()%60 <30) img=imageWalkWithHammer1Right;
				}
			}
			else{
				if(getX()%60 >29) img=imageStandRight;
				else if(getX()%60 <30) img=imageWalkRight;
			}
		}
	}
	
	public void animationClimb(){
		if(getClimb()){
			if(jump==false){
				if(getHammer()){
					if(!isStrike()){
						if(getY()%60>29) img = imageClimb1Hammer;
						if(getY()%60<30) img = imageClimb4Hammer;
					}
				}
				else{
					if(getY()%60>29) img = imageClimb1;
					if(getY()%60<30) img = imageClimb4;
				}
			}
		}
	}
	
	public void animationClimbEnd(){
		if(getClimbEnd()){
			if(getY()%20<10) img=imageClimb2;
			else if(getY()%20>9) img=imageClimb3;
		}
	}
	
	public void animationStrike(){
	}
	
	@Override
	public Image getImage() {
		animationx();
		
		animationJump();
		
		if(getClimbEnd()==false){
			animationClimb();
		}
		else{
			animationClimbEnd();
		}

		return img;
	}

	public boolean isStrike() {
		return strike;
	}

	public void setStrike(boolean strike) {
		this.strike = strike;
	}
}
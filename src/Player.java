import java.awt.*;
import javax.swing.*;

public class Player extends MyObjects implements Movable{
	Direction dirPl;	
	private int posX, posY, remainingLives;
	private Image imgRight= new ImageIcon(this.getClass().getResource("right.png")).getImage();
	private Image imgLeft= new ImageIcon(getClass().getResource("left.png")).getImage();
	private Image imgStrike= new ImageIcon(getClass().getResource("strike.png")).getImage();
	private Image imgJumpR= new ImageIcon(getClass().getResource("jumpRight.png")).getImage();
	private Image imgJumpL= new ImageIcon(getClass().getResource("jumpLeft.png")).getImage();
	private Image imgClimb1= new ImageIcon(getClass().getResource("climb1.png")).getImage();
	private Image imgClimb2 = new ImageIcon(getClass().getResource("climb2.png")).getImage();
	public Player(int posX, int posY){
		this.posX= super.getX();
		this.posY=super.getY();
		dirPl=Direction.E_Right;
	}
	
	public Image getRightImg(){
		return imgRight;
	}
	public Image getLeftImg(){
		return imgLeft;
	}
	public Image getStrikeImg(){
		return imgStrike;
	}
	public Image getJumpRImg(){
		return imgJumpR;
	}
	public Image getJumpLImg(){
		return imgJumpL;
	}
	public Image getClimb1Img(){
		return imgClimb1;
	}
	public Image getClimb2Img(){
		return imgClimb2;
	}
	public void setRemainingLifes( int remainedLives){
		remainingLives = remainedLives;
	}
	public int getRemainingLifes(){
		return remainingLives;
	}
	public void goUp(){
		posY+=1;
	}
	public void goDown(){
		posY-=1;
	}
	public void jump(){
		while(posY!= posY+3)
			posY+=1;
		while(posY!= posY-3)
			posY-=1;
	}
	
	public void goRight(){
		setX(getX()+1);
	}
	public void goLeft(){
		setX(getX()-1);
	}
	
	public void setDirection(Direction a){
		dirPl = a;
	}
	public Direction getDirection(){
		return dirPl;
	}
}
	

	
	


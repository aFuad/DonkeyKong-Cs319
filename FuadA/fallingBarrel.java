import java.awt.*;
import javax.swing.*;

public class fallingBarrel extends Enemy{
	
	private Image fallImg =  new ImageIcon(this.getClass().getResource("fallBarrel.png")).getImage();
	//fallImg;
	private int enemyType, posX, posY;
	private int lastPlatformY=1;
	public fallingBarrel(int posX, int posY, int enemyType){
		this.posX = super.getX();
		this.posY= super.getY();
		this.enemyType= enemyType;	
	}
	
	public void fallDown(){
		while(posY != lastPlatformY){
			posY-=2;
		}		
	}
	
	public Image getImageFall(){
		return fallImg;
	}
}

import java.awt.*;
import javax.swing.*;
public class rollingBarrel extends Enemy{
	
	private Image rollImg =  new ImageIcon(this.getClass().getResource("rollBarrel.png")).getImage();
	private Image fallImg =  new ImageIcon(this.getClass().getResource("fallBarrel.png")).getImage();
	//fallImg;
	private int enemyType, posX, posY;
	private int closestPlatformY=1;
	public rollingBarrel(int posX, int posY, int enemyType){
		this.posX = super.getX();
		this.posY= super.getY();
		this.enemyType= enemyType;	
	}
	
	public void fallDown(){
		while(posY != closestPlatformY){
			posY-=2;
		}		
	}
	
	public Image getImageRoll(){
		return rollImg;
	}
	
	public Image getImageFall(){
		return fallImg;
	}
}

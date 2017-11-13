import java.awt.*;
import javax.swing.*;

public class fireElemental extends Enemy{
	private Image imgRight =  new ImageIcon(this.getClass().getResource("fireRight.png")).getImage();
	private Image imgLeft =  new ImageIcon(this.getClass().getResource("fireLeft.png")).getImage();
	private int posX, posY, enemyType;
	public fireElemental(int posX, int posY, int enemyType){
		this.posX=super.getX();
		this.posY=super.getY();
		this.enemyType = enemyType;
	}
	public Image getImageRight(){
		return imgRight;
	}
	public Image getImageLeft(){
		return imgLeft;
	}
	public void goUp(){
		posY+=1;
	}
		public void goDown(){
		posY-=1;
	}
	
	
}


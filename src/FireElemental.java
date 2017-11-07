package source;

import java.awt.Image;

import javax.swing.ImageIcon;

public class FireElemental extends Enemy{
	private Image rightImage =  new ImageIcon(this.getClass().getResource("fireRight.png")).getImage();
	private Image leftImage =  new ImageIcon(this.getClass().getResource("fireLeft.png")).getImage();
	
	public FireElemental(int x, int y){
		super(x, y);
		super.setEnemyType(EnemyType.FIRE_ELEMENTAL);
	}
	
	public Image getImageRight(){
		return rightImage;
	}
	
	public Image getImageLeft(){
		return leftImage;
	}
	
	public void goUp(){
		setY(getY() + 1);
	}
	
	public void goDown(){
		setY(getY() - 1);
	}
}
package source;

import java.awt.Image;

import javax.swing.ImageIcon;

public class RollingBarrel extends Enemy{
	private Image imageRolling =  new ImageIcon(this.getClass().getResource("rollBarrel.png")).getImage();
	private Image imageFalling =  new ImageIcon(this.getClass().getResource("fallBarrel.png")).getImage();

	public RollingBarrel(int x, int y, EnemyType enemyType){
		super(x, y);
		super.setEnemyType(EnemyType.ROLLING_BARREL);
	}
	
	public void fallDown(){
		setY(getY() - 1);
	}
	
	public Image getImageRolling(){
		return imageRolling;
	}
	
	public Image getImageFalling(){
		return imageFalling;
	}
}
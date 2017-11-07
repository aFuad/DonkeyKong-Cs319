package source;

import java.awt.Image;

import javax.swing.ImageIcon;

public class FallingBarrel extends Enemy{
	private Image imageFallingBarrel =  new ImageIcon(this.getClass().getResource("fallBarrel.png")).getImage();
	
	public FallingBarrel(int x, int y){
		super(x, y);
		super.setEnemyType(EnemyType.FALLING_BARREL);
	}
	
	public void fallDown(){
		setY(getY() - 1);
	}
	
	public Image getImage(){
		return imageFallingBarrel;
	}
}
package source;

import java.awt.Image;

import javax.swing.ImageIcon;

public class FallingBarrel extends Barrel{
	private Image imageRoll1 =  new ImageIcon("src/image/25.png").getImage();
	private Image imageRoll2 =  new ImageIcon("src/image/26.png").getImage();
	private Image imageRoll3 =  new ImageIcon("src/image/27.png").getImage();
	private Image imageRoll4 =  new ImageIcon("src/image/28.png").getImage();
	private Image imageFall1 =  new ImageIcon("src/image/29.png").getImage();
	private Image imageFall2 =  new ImageIcon("src/image/30.png").getImage();
	private Image img=imageRoll1;
	
	public FallingBarrel(int x, int y){
		super(x, y);
		super.setEnemyType(EnemyType.FALLING_BARREL);
	}
	boolean falling;
	
	
	/*
	 * Getting a number lower than 0.25 and greater than 0.75 is harder than getting greater or lower than 0.5 due to implementation of random method.
	 * We do not want a total 50/50 scenario for selecting an barrel type.
	 */
	
	public void animation(){
		if(isFalling()==true){
			if(getY()%40<20) img=imageFall1;
			else if(getY()%40>19) img=imageFall2;
		}
		else if(isFalling()==false){
			if(getX()%100<25) img=imageRoll1;
			else if(getX()%120>24 && getX()%75<50) img=imageRoll2;
			else if(getX()%120>49 && getX()%75<75) img=imageRoll3;
			else if(getX()%40>74) img=imageRoll4;
		}
	}
	
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		animation();
		return img;
	}

	@Override
	public boolean continueFalling() {
		// TODO Auto-generated method stub
		if(Math.random() > 0.75 || Math.random() < 0.25){
			return true;
		}
		return false;
	}
}
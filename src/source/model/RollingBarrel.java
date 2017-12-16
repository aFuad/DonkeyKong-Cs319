package source.model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class RollingBarrel extends Barrel{
	private Image imageRoll1 =  new ImageIcon("src/image/19.png").getImage();
	private Image imageRoll2 =  new ImageIcon("src/image/20.png").getImage();
	private Image imageRoll3 =  new ImageIcon("src/image/21.png").getImage();
	private Image imageRoll4 =  new ImageIcon("src/image/22.png").getImage();
	private Image imageFall1 =  new ImageIcon("src/image/23.png").getImage();
	private Image imageFall2 =  new ImageIcon("src/image/24.png").getImage();
	private Image img= imageRoll1;

	public RollingBarrel(int x, int y){
		super(x, y);
		super.setEnemyType(EnemyType.ROLLING_BARREL);
	}

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
		return false;
	}
}
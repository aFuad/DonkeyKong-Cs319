package source;

import java.awt.Image;

import javax.swing.ImageIcon;

public class FireElemental extends Enemy{
	private Image imageFire1 =  new ImageIcon("src/image/49.png").getImage();
	private Image imageFire2 =  new ImageIcon("src/image/50.png").getImage();
	private Image imageFire3 =  new ImageIcon("src/image/50.png").getImage();
	private Image imageRight1 =  new ImageIcon("src/image/54.png").getImage();
	private Image imageRight2 =  new ImageIcon("src/image/55.png").getImage();
	private Image imageLeft1 =  new ImageIcon("src/image/52.png").getImage();
	private Image imageLeft2 =  new ImageIcon("src/image/53.png").getImage();
	private Image imageExp6 = new ImageIcon("src/image/49.png").getImage(); //50x100
	private Image imageExp7 = new ImageIcon("src/image/50.png").getImage(); //50x100
	private Image imageExp8 = new ImageIcon("src/image/51.png").getImage(); //50x100
	private Image img= imageExp8;
	
	private boolean goingUp;
	
	
	public FireElemental(int x, int y){
		super(x, y);
		super.setEnemyType(EnemyType.FIRE_ELEMENTAL);
		setGoingUp(false);
	}
	
	public void goUp(){
		setY(getY() - 1);
		relocateRectangle(getX(), getY());
	}
	
	public void gravity(){
		setY(getY() + 1);
		relocateRectangle(getX(), getY());
	}
	
	public void goRight(){
		setX(getX() + 1);
		relocateRectangle(getX(), getY());
	}
	
	public void goLeft(){
		setX(getX() - 1);
		relocateRectangle(getX(), getY());
	}

	public boolean isGoingUp() {
		return goingUp;
	}

	public void setGoingUp(boolean goingUp) {
		this.goingUp = goingUp;
	}

	public void animation(){
		if(img==imageExp8 && getX()%20<8) img=imageExp7;
		else if(img==imageExp7 && getX()%10>7 && getX()%10<15) img=imageExp6;
		else if(img==imageExp6 && getX()%10>14) img=imageExp6;
		if(isGoingUp()){
			if(getY()%100<25) img=imageLeft1;
			else if(getY()%100>24 && getY()%100<50) img=imageLeft2;
			else if(getY()%100>49 && getY()%100>75) img=imageRight1;
			else if(getY()%100>74) img=imageRight2;
		}
		else{
			if(getDirection()==Direction.LEFT){
				if(getX()%60<30) img=imageLeft1;
				else if(getX()%40>29) img=imageLeft2;
			}
			else if(getDirection()==Direction.RIGHT){
				if(getX()%60<30) img=imageRight1;
				else if(getX()%60>29) img=imageRight2;
			}
		}
	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		animation();
		return img;
	}
}
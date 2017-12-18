package source.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class FireElemental extends Enemy{
	private BufferedImage bufferedImage1;
	private BufferedImage bufferedImage2;
	private BufferedImage bufferedImage3;
	private BufferedImage bufferedImage4;
	private BufferedImage bufferedImage5;
	private BufferedImage bufferedImage6;
	private BufferedImage bufferedImage7;
	private Image imageRight1;
	private Image imageRight2;
	private Image imageLeft1;
	private Image imageLeft2;
	private Image imageExp6;
	private Image imageExp7;
	private Image imageExp8;
	private Image img;
	
	private boolean goingUp;
	
	
	public FireElemental(int x, int y){
		super(x, y);
		super.setEnemyType(EnemyType.FIRE_ELEMENTAL);
		setGoingUp(false);
		
		try {
			bufferedImage1 = ImageIO.read(getClass().getResource("/image/54.png"));
			bufferedImage2 = ImageIO.read(getClass().getResource("/image/55.png"));
			bufferedImage3 = ImageIO.read(getClass().getResource("/image/52.png"));			
			bufferedImage4 = ImageIO.read(getClass().getResource("/image/53.png"));			
			bufferedImage5 = ImageIO.read(getClass().getResource("/image/49.png"));			
			bufferedImage6 = ImageIO.read(getClass().getResource("/image/50.png"));
			bufferedImage7 = ImageIO.read(getClass().getResource("/image/51.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		imageRight1 =  new ImageIcon(bufferedImage1).getImage();
		imageRight2 =  new ImageIcon(bufferedImage2).getImage();
		imageLeft1 =  new ImageIcon(bufferedImage3).getImage();
		imageLeft2 =  new ImageIcon(bufferedImage4).getImage();
		imageExp6 = new ImageIcon(bufferedImage5).getImage();
		imageExp7 = new ImageIcon(bufferedImage6).getImage();
		imageExp8 = new ImageIcon(bufferedImage7).getImage();
		
		img= imageExp8;
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
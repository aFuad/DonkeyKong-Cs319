package source.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Monkey extends Nonmovable{
	private BufferedImage bufferedImage1;
	private BufferedImage bufferedImage2;
	private BufferedImage bufferedImage3;
	private BufferedImage bufferedImage4;
	private BufferedImage bufferedImage5;
	private BufferedImage bufferedImage6;
	private Image imageStand1;
	private Image imageLeft;
	//private Image imageStand2 =  new ImageIcon("src/image/39.png").getImage();
	private Image imageRight;
	private Image imageHappyLeft;
	private Image imageHappyRight;
	private Image imageWithRollingBarrel;
	//private Image imageWithFallingBarrel =  new ImageIcon("src/image/44.png").getImage();
	private Image img;
	private int cnt=1;
	
	private final boolean PASS_THROUGH = false;
	
	public Monkey(int x, int y){
		super(x, y);
		
		try {
			bufferedImage1 = ImageIO.read(getClass().getResource("/image/37.png"));
			bufferedImage2 = ImageIO.read(getClass().getResource("/image/38.png"));
			bufferedImage3 = ImageIO.read(getClass().getResource("/image/40.png"));			
			bufferedImage4 = ImageIO.read(getClass().getResource("/image/41.png"));			
			bufferedImage5 = ImageIO.read(getClass().getResource("/image/42.png"));			
			bufferedImage6 = ImageIO.read(getClass().getResource("/image/43.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		imageStand1 =  new ImageIcon(bufferedImage1).getImage();
		imageLeft =  new ImageIcon(bufferedImage2).getImage();
		imageRight =  new ImageIcon(bufferedImage3).getImage();
		imageHappyLeft =  new ImageIcon(bufferedImage4).getImage();
		imageHappyRight = new ImageIcon(bufferedImage5).getImage();
		imageWithRollingBarrel = new ImageIcon(bufferedImage6).getImage();
		
		img= imageStand1;
	}

	@Override
	public boolean getPassThrough(){
		return PASS_THROUGH;
	}
	
	public void animationMonkey(){		
		if(cnt==0) {
			img=imageStand1;
			++cnt;
		}
		else if(cnt==1) {
			img=imageHappyLeft;
			++cnt;
		}
		else if(cnt==2) {
			img=imageHappyRight;
			++cnt;
		}
		else if(cnt==3) {
			img=imageStand1;
			++cnt;
		}
		else if(cnt==4) {
			img=imageHappyLeft;
			++cnt;
		}
		else if(cnt==5) {
			img=imageHappyRight;
			++cnt;
		}
		else if(cnt==6) {
			img=imageStand1;
			++cnt;
		}
		else if(cnt==7) {
			img=imageLeft;
			++cnt;
		}
		else if(cnt==8) {
			img=imageWithRollingBarrel;
			++cnt;
		}
		else if(cnt==9) {
			img=imageRight;
			cnt=0;
		}
	}

	public Image getImage() {
		return img;
	}
}
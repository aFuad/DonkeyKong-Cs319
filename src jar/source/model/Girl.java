package source.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Girl extends Nonmovable{
	private BufferedImage bufferedImage1;
	private BufferedImage bufferedImage2;
	private BufferedImage bufferedImage3;
	private Image imageGirl1;
	private Image imageGirl2;
	private Image imageGirl3;
	private Image img;
	
	private final boolean PASS_THROUGH = false;
	
	public Girl(int x, int y){
		super(x, y);
		
		try {
			bufferedImage1 = ImageIO.read(getClass().getResource("/image/31.png"));
			bufferedImage2 = ImageIO.read(getClass().getResource("/image/32.png"));
			bufferedImage3 = ImageIO.read(getClass().getResource("/image/33.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageGirl1 =  new ImageIcon(bufferedImage1).getImage();
		imageGirl2 =  new ImageIcon(bufferedImage2).getImage();
		imageGirl3 =  new ImageIcon(bufferedImage3).getImage();
		
		img= imageGirl3;
	}

	@Override
	public boolean getPassThrough(){
		return PASS_THROUGH;
	}
	
	public void animationGirl(){
		if(img==imageGirl3) {
			img=imageGirl1;			
		}
		else if(img==imageGirl1) {
			img=imageGirl2;		
		}
		else if(img==imageGirl2) {
			img=imageGirl3;
		}
	}
	
	public Image getImage() {
		return img;
	}
}
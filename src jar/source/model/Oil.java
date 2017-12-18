package source.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Oil extends Nonmovable{
	private BufferedImage bufferedImage1;
	private BufferedImage bufferedImage2;
	private BufferedImage bufferedImage3;
	private BufferedImage bufferedImage4;
	private BufferedImage bufferedImage5;
	private Image imageOil1;
	private Image imageOil2;
	private Image imageOil3;
	private Image imageOil4;
	private Image imageOil5;
	private Image img=imageOil5;
	
	private final boolean PASS_THROUGH = false;
	
	private boolean lit;
	
	public Oil(int x, int y){
		super(x, y);
		lit = false;
		
		try {
			bufferedImage1 = ImageIO.read(getClass().getResource("/image/45.png"));
			bufferedImage2 = ImageIO.read(getClass().getResource("/image/46.png"));
			bufferedImage3 = ImageIO.read(getClass().getResource("/image/47.png"));			
			bufferedImage4 = ImageIO.read(getClass().getResource("/image/48.png"));			
			bufferedImage5 = ImageIO.read(getClass().getResource("/image/45newone.png"));			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		imageOil1 =  new ImageIcon(bufferedImage1).getImage();
		imageOil2 =  new ImageIcon(bufferedImage2).getImage();
		imageOil3 =  new ImageIcon(bufferedImage3).getImage();
		imageOil4 =  new ImageIcon(bufferedImage4).getImage();
		imageOil5 = new ImageIcon(bufferedImage5).getImage();
		
		img= imageOil5;
	}

	@Override
	public boolean getPassThrough(){
		return PASS_THROUGH;
	}
	
	public boolean getLit(){
		return lit;		
	}
	
	public void setLit(boolean lit){
		this.lit = lit;
	}
	
	public void animationOil(){
		if(getLit()==false)img=imageOil5;	
		else if(getLit() ==true){			
			if(img==imageOil5) img=imageOil1;
			else if(img==imageOil1) img=imageOil2;
			else if(img==imageOil2) img=imageOil3;
			else if(img==imageOil3) img=imageOil4;
			else img=imageOil1;
		}
	}
	

	public Image getImage() {
		return img;
	}
}
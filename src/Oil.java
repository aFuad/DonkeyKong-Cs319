package source;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Oil extends Nonmovable{
	private Image imageOil =  new ImageIcon("src/image/oil.png").getImage();
	
	private final boolean PASS_THROUGH = false;
	
	public Oil(int x, int y){
		super(x, y);
	}

	@Override
	public boolean getPassThrough(){
		// TODO Auto-generated method stub
		return PASS_THROUGH;
	}

	public Image getImage() {
		return imageOil;
	}
}

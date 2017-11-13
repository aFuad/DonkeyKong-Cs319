package source;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ExtraLife extends Nonmovable{
	private Image imageExtraLife =  new ImageIcon("src/image/extra life.png").getImage();
	
	private final boolean PASS_THROUGH = true;
	
	public ExtraLife(int x, int y){
		super(x, y);
	}

	@Override
	public boolean getPassThrough(){
		// TODO Auto-generated method stub
		return PASS_THROUGH;
	}

	public Image getImage() {
		return imageExtraLife;
	}
}

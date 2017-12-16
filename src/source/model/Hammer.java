package source.model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Hammer extends Nonmovable{
	private Image image =  new ImageIcon("src/image/34.png").getImage();
	
	private final boolean PASS_THROUGH = true;
	
	public Hammer(int x, int y){
		super(x, y);
	}

	@Override
	public boolean getPassThrough(){
		// TODO Auto-generated method stub
		return PASS_THROUGH;
	}

	public Image getImage() {
		return image;
	}
}
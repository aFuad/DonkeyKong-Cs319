package source;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Platform extends Nonmovable{
	private Image image =  new ImageIcon("src/image/35.png").getImage(); //50x50
	
	private final boolean PASS_THROUGH = false;
	
	public Platform(int x, int y){
		super(x,y);
	}

	public boolean getPassThrough(){
		return PASS_THROUGH;
	}

	public Image getImage() {
		return image;
	}
}
package source;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Girl extends Nonmovable{
	private Image imageGirl =  new ImageIcon("src/image/girl.png").getImage(); //50x100
	
	private final boolean PASS_THROUGH = false;
	
	public Girl(int x, int y){
		super(x, y);
	}

	@Override
	public boolean getPassThrough(){
		// TODO Auto-generated method stub
		return PASS_THROUGH;
	}

	public Image getImage() {
		return imageGirl;
	}
}

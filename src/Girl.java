package source;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Girl extends MyObject implements Nonmovable{
	private Image imageGirl =  new ImageIcon(this.getClass().getResource("girl.png")).getImage();
	
	private final boolean PASS_THROUGH = true;
	
	public Girl(int x, int y){
		super(x, y);
	}

	public boolean getPassThrough(){
		return PASS_THROUGH;
	}
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return super.getX();
	}
	
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return super.getY();
	}

	public Image getImage() {
		return imageGirl;
	}
}
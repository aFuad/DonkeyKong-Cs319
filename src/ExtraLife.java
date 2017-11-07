package source;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ExtraLife extends MyObject implements Nonmovable{
	private Image imageExtraLife =  new ImageIcon(this.getClass().getResource("life.png")).getImage();
	
	private final boolean PASS_THROUGH = true;
	
	public ExtraLife(int x, int y){
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
		return imageExtraLife;
	}
}
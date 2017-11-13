package source;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Monkey extends Nonmovable{
	private Image imageMonkey =  new ImageIcon("src/image/monkey.png").getImage(); // 100x100
	
	private final boolean PASS_THROUGH = false;
	
	public Monkey(int x, int y){
		super(x, y);
	}

	@Override
	public boolean getPassThrough(){
		// TODO Auto-generated method stub
		return PASS_THROUGH;
	}

	public Image getImage() {
		return imageMonkey;
	}
}

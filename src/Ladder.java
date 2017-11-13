package source;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Ladder extends Nonmovable{
	private Image imageLadder =  new ImageIcon("src/image/ladder.png").getImage();
	
	private final boolean PASS_THROUGH = true;
	
	public Ladder(int x, int y){
		super(x, y);
	}

	@Override
	public boolean getPassThrough(){
		// TODO Auto-generated method stub
		return PASS_THROUGH;
	}

	public Image getImage() {
		return imageLadder;
	}
}

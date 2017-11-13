import java.awt.*;
import javax.swing.ImageIcon;

public class Ladder extends MyObjects implements Nonmovable{
	private Image ladderImg =  new ImageIcon(this.getClass().getResource("ladder.png")).getImage();
	private int posX, posY;
	private boolean passThrough;
	public Ladder(){
		posX= super.getX();
		posY= super.getY();
	}
	public void setPassThrough(boolean a){
		passThrough = a;
	}
	public boolean getPassThrough(){
		return passThrough;
	}
}

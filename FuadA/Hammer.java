import java.awt.*;
import javax.swing.ImageIcon;

public class Hammer extends MyObjects implements Nonmovable{
	private Image hammerImg =  new ImageIcon(this.getClass().getResource("hammer.png")).getImage();
	private int posX, posY;
	private boolean passThrough;
	public Hammer(){
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

import java.awt.*;
import javax.swing.ImageIcon;

public class Platform extends MyObjects implements Nonmovable{
	private Image platformImg =  new ImageIcon(this.getClass().getResource("platform.png")).getImage();
	private int posX, posY;
	private boolean passThrough;
	public Platform(){
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

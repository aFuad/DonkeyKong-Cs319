import java.awt.*;
import javax.swing.ImageIcon;

public class Princess extends MyObjects implements Nonmovable{
	private Image princessImg =  new ImageIcon(this.getClass().getResource("princess.png")).getImage();
	private int posX, posY;
	private boolean passThrough;
	public Princess(){
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

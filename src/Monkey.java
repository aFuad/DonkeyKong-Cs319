import java.awt.*;
import javax.swing.ImageIcon;

public class Monkey extends MyObjects implements Nonmovable{
	private Image Monkey =  new ImageIcon(this.getClass().getResource("monkey.png")).getImage();
	private int posX, posY;
	private boolean passThrough;
	public Monkey(){
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

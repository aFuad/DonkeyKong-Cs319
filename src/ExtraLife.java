import java.awt.*;
import javax.swing.ImageIcon;

public class ExtraLife extends MyObjects implements Nonmovable{
	private Image exLifeImg =  new ImageIcon(this.getClass().getResource("life.png")).getImage();
	private int posX, posY;
	private boolean passThrough;
	public ExtraLife(){
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

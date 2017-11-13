import java.awt.*;
import javax.swing.ImageIcon;

public class Oil extends MyObjects implements Nonmovable{
	private Image oilImg =  new ImageIcon(this.getClass().getResource("oil.png")).getImage();
	private int posX, posY;
	private boolean passThrough;
	public Oil(){
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

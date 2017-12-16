package source.model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Monkey extends Nonmovable{
	private Image imageStand1 =  new ImageIcon("src/image/37.png").getImage();
	private Image imageLeft =  new ImageIcon("src/image/38.png").getImage();
	private Image imageStand2 =  new ImageIcon("src/image/39.png").getImage();
	private Image imageRight =  new ImageIcon("src/image/40.png").getImage();
	private Image imageHappyLeft =  new ImageIcon("src/image/41.png").getImage();
	private Image imageHappyRight =  new ImageIcon("src/image/42.png").getImage();
	private Image imageWithRollingBarrel =  new ImageIcon("src/image/43.png").getImage();
	private Image imageWithFallingBarrel =  new ImageIcon("src/image/44.png").getImage();
	private Image img=imageStand1;
	private int cnt=1;
	
	private final boolean PASS_THROUGH = false;
	
	public Monkey(int x, int y){
		super(x, y);
	}

	@Override
	public boolean getPassThrough(){
		// TODO Auto-generated method stub
		return PASS_THROUGH;
	}
	
	public void animationMonkey(){		
		if(cnt==0) {
			img=imageStand1;
			++cnt;
		}
		else if(cnt==1) {
			img=imageHappyLeft;
			++cnt;
		}
		else if(cnt==2) {
			img=imageHappyRight;
			++cnt;
		}
		else if(cnt==3) {
			img=imageStand1;
			++cnt;
		}
		else if(cnt==4) {
			img=imageHappyLeft;
			++cnt;
		}
		else if(cnt==5) {
			img=imageHappyRight;
			++cnt;
		}
		else if(cnt==6) {
			img=imageStand1;
			++cnt;
		}
		else if(cnt==7) {
			img=imageLeft;
			++cnt;
		}
		else if(cnt==8) {
			img=imageWithRollingBarrel;
			++cnt;
		}
		else if(cnt==9) {
			img=imageRight;
			cnt=0;
		}
	}

	public Image getImage() {
		return img;
	}
}
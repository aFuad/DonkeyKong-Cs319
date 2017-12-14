package source;

import java.awt.Image;
import java.lang.Object;
import javax.swing.ImageIcon;

public class Girl extends Nonmovable{
	private Image imageGirl1 =  new ImageIcon("src/image/31.png").getImage(); //50x100
	private Image imageGirl2 =  new ImageIcon("src/image/32.png").getImage(); //50x100
	private Image imageGirl3 =  new ImageIcon("src/image/33.png").getImage(); //50x100
	private Image img= imageGirl3;
	
	private final boolean PASS_THROUGH = false;
	
	public Girl(int x, int y){
		super(x, y);
	}

	@Override
	public boolean getPassThrough(){
		// TODO Auto-generated method stub
		return PASS_THROUGH;
	}
	
	public void waitt(){
		
	}
	
	public void animationGirl(){
		
		if(img==imageGirl3) {
			img=imageGirl1;			
		}
		else if(img==imageGirl1) {
			img=imageGirl2;		
		}
		else if(img==imageGirl2) {
			img=imageGirl3;
		}
		
	}
	
	public Image getImage() {
		
		return img;
	}
}
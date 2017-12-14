package source;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Oil extends Nonmovable{
	private Image imageOil1 =  new ImageIcon("src/image/45.png").getImage(); //50x100
	private Image imageOil2 =  new ImageIcon("src/image/46.png").getImage(); //50x100
	private Image imageOil3 =  new ImageIcon("src/image/47.png").getImage(); //50x100
	private Image imageOil4 =  new ImageIcon("src/image/48.png").getImage(); //50x100
	private Image imageOil5 =  new ImageIcon("src/image/45newone.png").getImage(); //50x100
	private Image img=imageOil5;
	
	private final boolean PASS_THROUGH = false;
	
	private boolean lit;
	
	public Oil(int x, int y){
		super(x, y);
		lit = false;
	}

	@Override
	public boolean getPassThrough(){
		// TODO Auto-generated method stub
		return PASS_THROUGH;
	}
	
	public boolean getLit(){
		System.out.println("==========="+lit);
		return lit;		
	}
	
	public void setLit(boolean lit){
		this.lit = lit;
	}
	
	public void update(){
		
	}
	
	public void animationOil(){
		
		if(getLit()==false)img=imageOil5;	
		else if(getLit() ==true){			
			if(img==imageOil5) img=imageOil1;
			else if(img==imageOil1) img=imageOil2;
			else if(img==imageOil2) img=imageOil3;
			else if(img==imageOil3) img=imageOil4;
			else img=imageOil1;
		}

	}
	

	public Image getImage() {
		
		return img;
	}
}
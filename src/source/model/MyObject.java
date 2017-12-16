package source.model;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class MyObject{
	private int x;
	private int y;
	
	private final static int SIDES = 50;
	
	private Rectangle rectangle;
	
	public MyObject(int x, int y){
		this.x = x * SIDES;
		this.y = y * SIDES;
		
		rectangle = new Rectangle(x * SIDES, y * SIDES, SIDES, SIDES);
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}
	
	public void relocateRectangle(int x, int y){
		rectangle.setLocation(x, y);
	}
	
	public abstract Image getImage();
}
package source;

import java.awt.Image;

public abstract class MyObject {
	private int x;
	private int y;
	
	public abstract int getX();
	
	public abstract void setX(int x);
	
	public abstract int getY();
	
	public abstract void setY(int y);
	
	public abstract Image getImage();
}

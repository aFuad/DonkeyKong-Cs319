package source;

import java.awt.Image;

public abstract class Nonmovable extends MyObject{
	
	public Nonmovable(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public abstract boolean getPassThrough();
}

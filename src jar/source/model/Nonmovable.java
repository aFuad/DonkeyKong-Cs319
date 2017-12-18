package source.model;

public abstract class Nonmovable extends MyObject{
	
	public Nonmovable(int x, int y) {
		super(x, y);
	}

	public abstract boolean getPassThrough();
}

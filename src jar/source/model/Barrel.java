package source.model;

public abstract class Barrel extends Enemy{
	private boolean falling;
	
	public Barrel(int x, int y) {
		super(x, y);
		falling = false;
	}

	public void fallDown(){
		setY(getY() + 5);
		relocateRectangle(getX(), getY());
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	public abstract boolean continueFalling();
}

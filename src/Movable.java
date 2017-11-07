package source;

public interface Movable {
	public void goRight();
	public void goLeft();
	public void setDirection(Direction a);
	public Direction getDirection();
}

public class Bomb extends Sprite {

	private final String bomb = "/img/bomb.png";
	private boolean destroyed;

	public Bomb(int x, int y) {
		setDestroyed(true);
		this.x = x;
		this.y = y;
		setImage(bomb); // Now uses proxy
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public boolean isDestroyed() {
		return destroyed;
	}
}
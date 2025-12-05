import javax.swing.ImageIcon;

public class GameOver extends Sprite implements Commons {

	private final String gameOver = "/img/gameover.png";
	private int width;

	public GameOver() {
		setImage(gameOver); // Now uses proxy

		// Width: Temp load for width
		ImageIcon ii = new ImageIcon(getClass().getResource(gameOver));
		setWidth(ii.getImage().getWidth(null));

		setX(0);
		setY(0);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
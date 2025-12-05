import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons {

	private final int START_Y = 400;
	private final int START_X = 270;

	private final String player = "/img/craft.png";
	private int width;

	public Player() {
		setImage(player); // Now uses proxy via setImage(String)

		// Width calculation: Since lazy-load, we can't get width immediately.
		// For simplicity, hardcode or load temporarily (assuming original did it after
		// load)
		ImageIcon ii = new ImageIcon(getClass().getResource(player)); // Temp load for width
		width = ii.getImage().getWidth(null);

		setX(START_X);
		setY(START_Y);
	}

	public void act() {
		x += dx;
		if (x <= 2)
			x = 2;
		if (x >= BOARD_WIDTH - 2 * width)
			x = BOARD_WIDTH - 2 * width;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = -2;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 2;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}
}
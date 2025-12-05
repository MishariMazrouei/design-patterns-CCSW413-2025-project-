import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, Commons {

	private static final long serialVersionUID = 1L;

	private Dimension d;
	private ArrayList<Alien> aliens; // Changed to generics
	private Player player;
	private Shot shot;
	private GameOver gameend;
	private Won vunnet;

	private int alienX = 150;
	private int alienY = 25;
	private int direction = -1;
	private int deaths = 0;

	private boolean ingame = true;
	private boolean havewon = true;
	private final String expl = "/img/explosion.png";
	private final String alienpix = "/img/alien.png";
	private String message = "Your planet belongs to us now...";

	private Thread animator;

	public Board() {
		// Removed addKeyListener and setFocusable(true) - handled by SpaceInvaders via
		// facade
		d = new Dimension(BOARD_WIDTH, BOARD_HEIGTH);
		setBackground(Color.black);
		setDoubleBuffered(true);
	}

	public void gameInit() {
		aliens = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				Alien alien = new Alien(alienX + 18 * j, alienY + 18 * i);
				alien.setImage(alienpix); // Uses proxy
				aliens.add(alien);
			}
		}

		player = new Player();
		shot = new Shot();

		if (animator == null || !ingame) {
			animator = new Thread(this);
			animator.start();
		}
	}

	public void drawAliens(Graphics g) {
		Iterator<Alien> it = aliens.iterator();

		while (it.hasNext()) {
			Alien alien = it.next();

			if (alien.isVisible()) {
				g.drawImage(alien.getImage().getAwtImage(), alien.getX(), alien.getY(), this); // Via proxy
			}

			if (alien.isDying()) {
				alien.die();
			}
		}
	}

	public void drawPlayer(Graphics g) {
		if (player.isVisible()) {
			g.drawImage(player.getImage().getAwtImage(), player.getX(), player.getY(), this); // Via proxy
		}

		if (player.isDying()) {
			player.die();
			havewon = false;
			ingame = false;
		}
	}

	public void drawGameEnd(Graphics g) {
		g.drawImage(gameend.getImage().getAwtImage(), 0, 0, this); // Via proxy
	}

	public void drawShot(Graphics g) {
		if (shot.isVisible())
			g.drawImage(shot.getImage().getAwtImage(), shot.getX(), shot.getY(), this); // Via proxy
	}

	public void drawBombing(Graphics g) {
		Iterator<Alien> i3 = aliens.iterator();

		while (i3.hasNext()) {
			Alien a = i3.next();

			Bomb b = a.getBomb();

			if (!b.isDestroyed()) {
				g.drawImage(b.getImage().getAwtImage(), b.getX(), b.getY(), this); // Via proxy
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.black);
		g.fillRect(0, 0, d.width, d.height);
		g.setColor(Color.green);

		if (ingame) {
			g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
			drawAliens(g);
			drawPlayer(g);
			drawShot(g);
			drawBombing(g);
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void gameOver() {
		Graphics g = this.getGraphics();

		gameend = new GameOver();
		vunnet = new Won();

		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);
		if (havewon) { // Assuming this was the truncated part; adjust if needed
			g.drawImage(vunnet.getImage().getAwtImage(), 0, 0, this);
		} else {
			drawGameEnd(g);
		}

		Font font = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = this.getFontMetrics(font);

		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message)) / 2,
				BOARD_WIDTH / 2);
	}

	public void animationCycle() {

		if (deaths == NUMBER_OF_ALIENS_TO_DESTROY) {
			havewon = true;
			ingame = false;
			message = "You saved the Earth!";
		}

		// player

		player.act();

		// shot
		if (shot.isVisible()) {
			Iterator<Alien> it = aliens.iterator();

			int shotX = shot.getX();
			int shotY = shot.getY();

			while (it.hasNext()) {
				Alien alien = it.next();
				int alienX = alien.getX();
				int alienY = alien.getY();

				if (alien.isVisible() && shot.isVisible()) {
					if (shotX >= (alienX) && shotX <= (alienX + ALIEN_WIDTH)
							&& shotY >= (alienY)
							&& shotY <= (alienY + ALIEN_HEIGHT)) {
						alien.setImage(expl); // Fixed: Use the path string to set explosion via proxy
						alien.setDying(true);
						deaths++;
						shot.die();
					}
				}
			}

			int y = shot.getY();
			y -= 8;
			if (y < 0)
				shot.die();
			else
				shot.setY(y);
		}

		// aliens

		Iterator<Alien> it1 = aliens.iterator();

		while (it1.hasNext()) {
			Alien a1 = it1.next();
			int x = a1.getX();

			if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {
				direction = -1;
				Iterator<Alien> i1 = aliens.iterator();
				while (i1.hasNext()) {
					Alien a2 = i1.next();
					a2.setY(a2.getY() + GO_DOWN);
				}
			}

			if (x <= BORDER_LEFT && direction != 1) {
				direction = 1;

				Iterator<Alien> i2 = aliens.iterator();
				while (i2.hasNext()) {
					Alien a = i2.next();
					a.setY(a.getY() + GO_DOWN);
				}
			}
		}

		Iterator<Alien> it = aliens.iterator();

		while (it.hasNext()) {
			Alien alien = it.next();
			if (alien.isVisible()) {

				int y = alien.getY();

				if (y > GROUND - ALIEN_HEIGHT) {
					havewon = false;
					ingame = false;
					message = "Aliens are invading the galaxy!";
				}

				alien.act(direction);
			}
		}

		// bombs

		Iterator<Alien> i3 = aliens.iterator();
		Random generator = new Random();

		while (i3.hasNext()) {
			int shotChance = generator.nextInt(15);
			Alien a = i3.next();
			Bomb b = a.getBomb();
			if (shotChance == CHANCE && a.isVisible() && b.isDestroyed()) {

				b.setDestroyed(false);
				b.setX(a.getX());
				b.setY(a.getY());
			}

			int bombX = b.getX();
			int bombY = b.getY();
			int playerX = player.getX();
			int playerY = player.getY();

			if (player.isVisible() && !b.isDestroyed()) {
				if (bombX >= (playerX) && bombX <= (playerX + PLAYER_WIDTH)
						&& bombY >= (playerY)
						&& bombY <= (playerY + PLAYER_HEIGHT)) {
					player.setImage(expl); // Fixed: Use the path string to set explosion via proxy
					player.setDying(true);
					b.setDestroyed(true);
				}
			}

			if (!b.isDestroyed()) {
				b.setY(b.getY() + 1);
				if (b.getY() >= GROUND - BOMB_HEIGHT) {
					b.setDestroyed(true);
				}
			}
		}
	}

	public void run() {
		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (ingame) {
			repaint();
			animationCycle();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;

			if (sleep < 0)
				sleep = 1;
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
			beforeTime = System.currentTimeMillis();
		}
		gameOver();
	}

	// Made public for facade delegation
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);

		int x = player.getX();
		int y = player.getY();

		if (ingame) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_SPACE) {
				if (!shot.isVisible())
					shot = new Shot(x, y);
			}
		}
	}

	// Made public for facade delegation
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}

	// Getters for facade
	public int getDeaths() {
		return deaths;
	}

	public boolean isInGame() {
		return ingame;
	}
}
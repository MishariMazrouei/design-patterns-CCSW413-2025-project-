import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class GameFacade implements Commons {
    private Board board;

    public GameFacade() {
        board = new Board();
    }

    public void startGame() {
        board.gameInit(); // Initialize aliens, player, etc.
        board.setPreferredSize(new java.awt.Dimension(BOARD_WIDTH, BOARD_HEIGTH));
        // Animator thread started in Board.gameInit()
    }

    public void handleKeyPressed(KeyEvent e) {
        board.keyPressed(e); // Delegate to Board's key handling (which calls player/shot)
    }

    public void handleKeyReleased(KeyEvent e) {
        board.keyReleased(e);
    }

    public int getDeaths() {
        return board.getDeaths();
    }

    public boolean isInGame() {
        return board.isInGame();
    }

    public JPanel getGamePanel() {
        return board;
    }
}
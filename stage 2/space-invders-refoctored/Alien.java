
public class Alien extends Sprite {

    private Bomb bomb;
    private final String alien = "/img/alien.png";

    public Alien(int x, int y) {
        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);
        setImage(alien); // Now uses proxy
    }

    public void act(int direction) {
        this.x += direction;
    }

    public Bomb getBomb() {
        return bomb;
    }
}
import javax.swing.ImageIcon;

public class Won extends Sprite implements Commons {

    private final String won = "/img/won.jpg";
    private int width;

    public Won() {
        setImage(won); // Now uses proxy

        // Width: Temp load for width
        ImageIcon ii = new ImageIcon(getClass().getResource(won));
        width = ii.getImage().getWidth(null);

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
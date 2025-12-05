import javax.swing.ImageIcon;
import java.awt.Image;

public class RealGameImage implements GameImage {
    private Image image;
    private String path;

    public RealGameImage(String path) {
        this.path = path;
        loadImage();
    }

    private void loadImage() {
        image = new ImageIcon(getClass().getResource(path)).getImage();
    }

    @Override
    public Image getAwtImage() {
        return image;
    }
}
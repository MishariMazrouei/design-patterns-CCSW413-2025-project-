import java.awt.Image;

public class ImageProxy implements GameImage {
    private RealGameImage realImage;
    private String path;

    public ImageProxy(String path) {
        this.path = path;
    }

    @Override
    public Image getAwtImage() {
        if (realImage == null) {
            realImage = new RealGameImage(path); // Lazy load
        }
        return realImage.getAwtImage();
    }
}
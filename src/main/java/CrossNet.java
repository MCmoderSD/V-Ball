import javax.swing.*;
import java.awt.*;

public class CrossNet {
    private final int x, y, width, height;
    private final Point position;
    private final ImageIcon image;

    public CrossNet(JPanel panel, Config config) {
        image = Utils.createImageIcon(config.getCrossNet());

        width = image.getIconWidth();
        height = image.getIconHeight();

        x = (panel.getWidth() / 2) - (width / 2);
        y = (panel.getHeight()) - (height);

        this.position = new Point(x, y);
    }

    public ImageIcon getImage() {
        return image;
    }

    public Point getPosition() {
        return position;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}

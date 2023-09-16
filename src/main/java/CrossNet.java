import javax.swing.*;
import java.awt.*;

public class CrossNet {
    private final int x, y, width, height;
    private final Image image;
    private final Rectangle hitbox;

    public CrossNet(JPanel panel, Config config) {
        image = Utils.reader(config.getCrossNet());

        width = image.getWidth(null);
        height = image.getHeight(null);

        x = (panel.getWidth()- width) /2;
        y = (panel.getHeight()) - (height/2);

        hitbox = new Rectangle(x, y, width, height);
    }

    public Image getImage() {
        return image;
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
        return hitbox;
    }
}

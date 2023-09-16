import javafx.scene.shape.Circle;

import java.awt.*;

public class Ball {
    private final int radius;
    private final Image image;
    private int x;
    private int y;
    private Circle hitbox;

    public Ball(Config config) {
        image = Utils.reader(config.getBall());
        radius = image.getWidth(null);
        hitbox = new Circle(x + radius, y + radius, radius);
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        hitbox.setCenterX(x + radius);
        hitbox.setCenterY(y + radius);
    }


    public void setX(int x) {
        this.x = x;
        hitbox.setCenterX(x + radius);
        hitbox.setCenterY(y + radius);
    }

    public void setY(int y) {
        this.y = y;
        hitbox.setCenterX(x + radius);
        hitbox.setCenterY(y + radius);
    }

    public Image getImage() {
        return image;
    }

    public Circle getBounds() {
        return hitbox;
    }

    public int getRadius() {
        return radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

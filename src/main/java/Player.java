import javax.swing.*;
import java.awt.*;

public class Player {
    private final Image image;
    private final int speed = 1;
    private final int jumpHeight = 100;
    private final int width, height;
    private final boolean isLeft;
    private Point position;
    private Rectangle hitbox;
    private int x;
    private int y;

    private int fallSpeed;
    private int score;
    private int lives;

    public Player(Config config, boolean isLeft) {
        this.isLeft = isLeft;
        if (isLeft) image = Utils.reader(config.getPlayer()[0]);
        else image = Utils.reader(config.getPlayer()[1]);


        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        position = new Point(x, y);
        hitbox = new Rectangle(x, y, width, height);
    }

    public void setLocation(Point position) {
        this.position = position;
        this.x = position.x;
        this.y = position.y;
        hitbox = new Rectangle(x, y, width, height);
    }

    public void setX(int x) {
        this.x = x;
        position = new Point(x, y);
        hitbox = new Rectangle(x, y, width, height);
    }

    public void setY(int y) {
        this.y = y;
        position = new Point(x, y);
        hitbox = new Rectangle(x, y, width, height);
    }

    public Image getImage() {
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
        return hitbox;
    }

    public void move(Controls controls, GamePanel panel) {

        if (y < panel.getConfig().getFrameHeight() - getHeight()) y += fallSpeed;

        if (isLeft) {
            if (controls.wPressed && y >= (panel.getConfig().getFrameHeight() - getHeight())) y -= jumpHeight;
            if (controls.dPressed && x <= panel.getCrossNet().getX() - getWidth() - speed) x += speed;
            if (controls.aPressed && x >= speed) x -= speed;
        } else {
            if (controls.upPressed && y >= (panel.getConfig().getFrameHeight() - getHeight())) y -= jumpHeight;
            if (controls.rightPressed && x <= panel.getConfig().getFrameWidth() - getWidth() - speed) x += speed;
            if (controls.leftPressed && x >= panel.getCrossNet().getX() + panel.getCrossNet().getWidth() + speed) x -= speed;
        }

        if (fallSpeed <= 0) fallSpeed += 2;

        hitbox = new Rectangle(x, y, width, height);
    }
}
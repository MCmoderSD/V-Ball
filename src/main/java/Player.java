import javax.swing.*;
import java.awt.*;

public class Player {
    private final ImageIcon image;
    private final int player;
    private Point position;
    private int x;
    private int y;
    private int speed = 10;
    private int score;
    private int lives;


    public Player(Config config, int player) {
        image = Utils.createImageIcon(config.getPlayer()[player]);
        this.player = player;
    }

    public Player(Point position, Config config, int player) {
        this.position = position;
        this.x = position.x;
        this.y = position.y;
        image = Utils.createImageIcon(config.getPlayer()[player]);
        this.player = player;
    }

    public Player(int x, int y, Config config, int player) {
        this.x = x;
        this.y = y;
        position = new Point(x, y);
        image = Utils.createImageIcon(config.getPlayer()[player]);
        this.player = player;
    }


    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        position = new Point(x, y);
    }

    public void setLocation(Point position) {
        this.position = position;
        this.x = position.x;
        this.y = position.y;
    }

    public void setX(int x) {
        this.x = x;
        position = new Point(x, y);
    }

    public void setY(int y) {
        this.y = y;
        position = new Point(x, y);
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
        return image.getIconWidth();
    }

    public int getHeight() {
        return image.getIconHeight();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getIconWidth(), image.getIconHeight());
    }

    public void move(Controls controls, JPanel panel) {

        if (player == 0) {
            if (controls.wPressed) y -= speed;
            if (controls.sPressed) y += speed;
            if (controls.dPressed) x += speed;
            if (controls.aPressed) x -= speed;
        } else if (player == 1) {
            if (controls.upPressed) y -= speed;
            if (controls.downPressed) y += speed;
            if (controls.rightPressed) x += speed;
            if (controls.leftPressed) x -= speed;
        }
    }
}

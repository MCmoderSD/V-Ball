import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    private final Thread thread;
    private final Controls controls;
    private final Config config;
    private final Player playerLeft, playerRight;
    private final CrossNet crossNet;
    private final Ball ball;
    private boolean debug = true;
    public GamePanel(Config config) {
        this.config = config;

        setLayout(null);
        setBounds(0, 0, config.getFrameWidth(), config.getFrameHeight());
        setPreferredSize(new Dimension(config.getFrameWidth(), config.getFrameHeight()));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        controls = new Controls();

        addKeyListener(controls);
        setFocusable(true);


        playerLeft = new Player(config, true);
        playerRight = new Player(config, false);

        playerLeft.setLocation((getHeight() / 4) - (playerLeft.getWidth() / 2), getHeight() - playerLeft.getHeight());
        playerRight.setLocation((3 * (getWidth() / 4)) - (playerRight.getWidth() / 2), getHeight() - playerRight.getHeight());

        crossNet = new CrossNet(this, config);

        ball = new Ball(config);
        ball.setLocation((getWidth() - ball.getRadius()) / 2, (getHeight() - ball.getRadius()) / 2);

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        double tickrate = 1000000000 / config.getFrameRate();
        double delta = 0;
        long now = System.nanoTime();
        long current;

        while (thread.isAlive()) {
            current = System.nanoTime();
            delta += (current - now) / tickrate;
            now = current;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        playerLeft.move(controls, this);
        playerRight.move(controls, this);
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g = (Graphics2D) graphics;

        g.drawImage(playerLeft.getImage(), playerLeft.getX(), playerLeft.getY(), this);
        g.drawImage(playerRight.getImage(), playerRight.getX(), playerRight.getY(), this);
        g.drawImage(crossNet.getImage(), crossNet.getX(), crossNet.getY(), this);
        g.drawImage(ball.getImage(), ball.getX(), ball.getY(), this);

        if (debug) {
            g.setColor(Color.WHITE);
            g.draw(crossNet.getBounds());
            g.draw(playerLeft.getBounds());
            g.draw(playerRight.getBounds());
            g.drawOval(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
        }
        g.dispose();
    }

    public Controls getControls() {
        return controls;
    }

    public Player getPlayerLeft() {
        return playerLeft;
    }

    public Player getPlayerRight() {
        return playerRight;
    }

    public CrossNet getCrossNet() {
        return crossNet;
    }

    public Config getConfig() {
        return config;
    }
}

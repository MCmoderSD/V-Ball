import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    private final Thread thread;
    private final Controls controls;
    public final Config config;
    private final Player playerLeft, playerRight;
    private final CrossNet crossNet;
    public Rectangle rPlayerLeft, rPlayerRight, rCrossNet;
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


        playerLeft = new Player(config, 0);
        playerRight = new Player(config, 1);

        playerLeft.setLocation((getHeight() / 4) - (playerLeft.getWidth() / 2), getHeight() - playerLeft.getHeight());
        playerRight.setLocation((3 * (getWidth() / 4)) - (playerRight.getWidth() / 2), getHeight() - playerRight.getHeight());

        crossNet = new CrossNet(this, config);
        rCrossNet = crossNet.getBounds();

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
        rPlayerLeft = playerLeft.getBounds();
        rPlayerRight = playerRight.getBounds();

        playerLeft.move(controls, this);
        playerRight.move(controls, this);
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g = (Graphics2D) graphics;

        g.drawImage(playerLeft.getImage().getImage(), playerLeft.getX(), playerLeft.getY(), this);
        g.drawImage(playerRight.getImage().getImage(), playerRight.getX(), playerRight.getY(), this);
        g.drawImage(crossNet.getImage().getImage(), crossNet.getX(), crossNet.getY(), this);

        g.dispose();
    }
}

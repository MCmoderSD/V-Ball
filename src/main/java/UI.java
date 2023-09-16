import javax.swing.*;

public class UI {
    public UI(Config config){
        JFrame frame = new JFrame(config.getTitle());
        frame.setResizable(config.isResizable());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel(config);
        frame.add(gamePanel);
        frame.pack();

        frame.setLocation(Utils.centerFrame(frame));
        frame.setVisible(true);
    }
}

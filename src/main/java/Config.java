import com.fasterxml.jackson.databind.JsonNode;

public class Config {
    private final String[] args;

    private final String title;
    private final int frameWidth;
    private final int frameHeight;
    private final int frameRate;
    private final String background;
    private final String[] player = new String[2];
    private final String ball, crossNet;
    private boolean isResizable;


    public Config(String[] args) {
        this.args = args;

        JsonNode config = Utils.readJson("config");

        assert config != null;
        title = config.get("title").asText();
        frameWidth = config.get("frameWidth").asInt();
        frameHeight = config.get("frameHeight").asInt();
        frameRate = config.get("frameRate").asInt();
        //background = config.get("background").asText();
        player[0] = config.get("playerLeft").asText();
        player[1] = config.get("playerRight").asText();
        crossNet = config.get("crossNet").asText();
        ball = config.get("ball").asText();

        background = null;


        new UI(this);
    }

    public String getTitle() {
        return title;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }
    public double getFrameRate() {
        return frameRate;
    }

    public String getBackground() {
        return background;
    }
    public String getCrossNet() {
        return crossNet;
    }

    public String[] getPlayer() {
        return player;
    }

    public String getBall() {
        return ball;
    }

    public String[] getArgs() {
        return args;
    }

    public boolean isResizable() {
        return isResizable;
    }
}

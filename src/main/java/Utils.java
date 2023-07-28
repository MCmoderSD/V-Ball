// Dependencies
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// Normal imports
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

public class Utils {


    private static final HashMap<String, BufferedImage> bufferedImageCache = new HashMap<>(); // Cache for BufferedImages
    private static final HashMap<String, ImageIcon> imageIconCache = new HashMap<>(); // Cache for ImageIcons


    public static BufferedImage reader(String resource) {
        if (bufferedImageCache.containsKey(resource)) return bufferedImageCache.get(resource); // Checks if the path has already been loaded
        BufferedImage image = null;
        try {
            if (resource.endsWith(".png")) { // Checks if the image is a .png
                //image = ImageIO.read(Files.newInputStream(Paths.get(resource))); // Image is local
                image = ImageIO.read(Objects.requireNonNull(Utils.class.getResource(resource))); // Image is in the JAR file
            } else throw new IllegalArgumentException("The image format is not supported: " + resource);

            bufferedImageCache.put(resource, image); // Adds the image to the cache

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image == null) throw new IllegalArgumentException("The image could not be loaded: " + resource);
        return image;
    }

    public static ImageIcon createImageIcon(String resource) {
        if (imageIconCache.containsKey(resource)) return imageIconCache.get(resource); // Checks if the path has already been loaded
        ImageIcon imageIcon;
        if (resource.endsWith(".png")) {
            imageIcon = new ImageIcon(reader(resource)); // Creates an ImageIcon
        } else if (resource.endsWith(".gif")) {
            URL imageUrl = Utils.class.getClassLoader().getResource(resource);
            imageIcon = new ImageIcon(Objects.requireNonNull(imageUrl));
        } else throw new IllegalArgumentException("The image format is not supported: " + resource);

        imageIconCache.put(resource, imageIcon); // Adds the image to the cache
        return imageIcon;
    }

    public static Point centerFrame(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Screen Size
        int x = ((screenSize.width - frame.getWidth()) / 2);
        int y = ((screenSize.height - frame.getHeight()) / 2);
        return new Point(x, y);
    }

    public static JsonNode readJson(String json)  {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream;
            if (json.endsWith(".json")) inputStream = Files.newInputStream(Paths.get(json)); // JSON is Local
            else inputStream = Utils.class.getResourceAsStream("config/" + json + ".json"); // JSON is in Jar
            if (inputStream == null) return null;
            return mapper.readTree(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

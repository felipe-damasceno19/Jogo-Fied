package cutscene;

import java.awt.image.BufferedImage;

public class Cutscene {
    public BufferedImage image;
    public String[] lines;

    public Cutscene(BufferedImage image, String[] lines) {
        this.image = image;
        this.lines = lines;
    }
}


package org.ichcode.version4;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;

public class SkinManager {

    private static final Map<String, BufferedImage> skins = new HashMap<>();

    public static void loadSkin(String name, String path) {
        try {
            BufferedImage img = ImageIO.read(
                    Objects.requireNonNull(SkinManager.class.getResource(path))
            );
            skins.put(name, img);
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error cargando skin: " + path);
        }
    }

    public static BufferedImage getSkin(String name) {
        return skins.get(name);
    }
}


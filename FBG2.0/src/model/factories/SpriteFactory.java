package model.factories;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.map.Direction;

/**
 *
 * @author ChrisMoscoso
 */
public class SpriteFactory {

    /* NORTH AND SOUTH HAVE TO BE FLIPPED */
    private static final String resourcePath = "src/resources/img/";

    private static final String LIGHT_GRASS = resourcePath + "tile/LightGrass.png";
    private static final String LIGHT_WATER = resourcePath + "tile/LightWater.png";
    private static final String LIGHT_MOUNTAIN = resourcePath + "tile/LightMountain.png";
    private static final String DARK_GRASS = resourcePath + "tile/DarkGrass.png";
    private static final String DARK_WATER = resourcePath + "tile/DarkWater.png";
    private static final String DARK_MOUNTAIN = resourcePath + "tile/DarkMountain.png";
    
    

    public static BufferedImage getAvatar(Direction d) {
        BufferedImage img = null;
        String path;
        switch (d) {
            case North:
                path = resourcePath + "summoner/south_idle.png";
                break;
            case NorthEast:
                path = resourcePath + "summoner/east_idle.png";
                break;
            case East:
                path = resourcePath + "summoner/east_idle.png";
                break;
            case SouthEast:
                path = resourcePath + "summoner/east_idle.png";
                break;
            case South:
                path = resourcePath + "summoner/north_idle.png";
                break;
            case SouthWest:
                path = resourcePath + "summoner/north_idle.png";
            case West:
                path = resourcePath + "summoner/west_idle.png";
                break;
            case NorthWest:
                path = resourcePath + "summoner/south_idle.png";
                break;
            default:
                path = resourcePath + "summoner/north_idle.png";

        }

        try {
            img = ImageIO.read(new File(path));
        } catch (Exception e) {
        }

        return img;
    }

    public static BufferedImage getGenericObject() {
        return getImage(resourcePath + "generic.png");
    }

    private static BufferedImage getImage(String filename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filename));
        } catch (IOException e) { System.out.println(filename + e);}

        return img;
    }

    public static BufferedImage getGrass() {
        return getImage(resourcePath + "tile/LightGrass.png");
    }

    public static BufferedImage getFog() {
        return getImage(resourcePath + "tile/fog.png");
    }

    public static BufferedImage getFireball() {
        return getImage(resourcePath + "projectile/fireball.png");
    }

    public static BufferedImage hashIDtoImage(String id) {
        switch (id) {
            case "1":
                return getImage(LIGHT_GRASS);
            case "2":
                return getImage(LIGHT_WATER);
            case "3":
                return getImage(LIGHT_MOUNTAIN);
            case "4":
                return getImage(DARK_GRASS);
            case "5":
                return getImage(DARK_WATER);
            case "6":
                return getImage(DARK_MOUNTAIN);
            default:
                return getGenericObject();

        }
    }

    public static BufferedImage getGenericEntity(Direction direction) {
        return getGenericObject();
    }
}

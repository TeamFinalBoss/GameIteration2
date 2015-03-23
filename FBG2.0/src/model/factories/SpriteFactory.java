package model.factories;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import model.map.Direction;

/**
 *
 * @author ChrisMoscoso
 */
public class SpriteFactory {

    
    /* NORTH AND SOUTH HAVE TO BE FLIPPED */
    
    private static String resourcePath = "src/resources/img/";

    public static BufferedImage getAvatar(Direction d) {
        BufferedImage img = null;
        String path = "";
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
        
        try{
            img = ImageIO.read(new File(path));
        }catch(Exception e){}
        
        return img;
    }

    public static BufferedImage getGenericEntity(Direction direction) {
        return getImage(resourcePath + "summonerUp.gif");
    }
    
    private static BufferedImage getImage(String filename){
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(filename));
        }catch(Exception e){}
        
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
}

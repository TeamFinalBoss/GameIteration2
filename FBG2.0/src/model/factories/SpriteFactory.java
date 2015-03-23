package model.factories;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.item.Gun;
import model.item.Halo;
import model.item.HealthPotion;
import model.item.HermesBoots;
import model.item.Hornes;
import model.item.JesusBoots;
import model.item.Mace;
import model.item.RecoveryHeart;
import model.item.RustyKnife;
import model.item.Stick;
import model.item.Sword;
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
    private static final String GUN = resourcePath + "item/gun.png";
    private static final String HEALTH_POTION = resourcePath + "item/RecoveryHeart.png";
    private static final String HALO = resourcePath + "item/Halo.png";
    private static final String HERMES_BOOTS = resourcePath + "item/HermesBoots.png";
    //private static final String JESUS_BOOTS = resourcePath + "item/gun.png";
    private static final String RUSTY_KNIFE = resourcePath + "item/RustyKnife.png";
    private static final String MACE = resourcePath + "item/Mace.png";
    private static final String STICK = resourcePath + "item/Stick.png";
    private static final String HORNES = resourcePath + "item/Hornes.png";
    private static final String RECOVERY_HEART = resourcePath + "item/RecoveryHeart.png";
    private static final String DOOR_OPENER = resourcePath + "item/DoorOpener.png";
    private static final String DOOR = resourcePath + "item/Door.png";

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
        } catch (IOException e) {
            System.out.println(filename + e);
        }

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
            case "gun":
                return getImage(GUN);

            case "healthPotion":
                return getImage(HEALTH_POTION);

            case "halo":
                return getImage(HALO);

            case "hermesBoots":
                return getImage(HERMES_BOOTS);

            case "jesusBoots":
            //return getImage(JESUS_BOOTS);

            case "rustyKnife":
                return getImage(RUSTY_KNIFE);

            case "mace":
                return getImage(MACE);

            case "stick":
                return getImage(STICK);

            case "hornes":
                return getImage(HORNES);

            case "sword":
            //return getImage(SWORD);

            case "recoveryHeart":
                return getImage(RECOVERY_HEART);

            case "doorOpener":
                return getImage(DOOR_OPENER);
            case "door":
                return getImage(DOOR);

            default:
                System.out.println(id + " wheres the pic file?");
                return getGenericObject();

        }
    }

    public static BufferedImage getGenericEntity(Direction direction) {
        return getGenericObject();
    }
}

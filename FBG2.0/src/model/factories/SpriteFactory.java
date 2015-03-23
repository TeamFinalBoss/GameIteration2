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

    private static SpriteFactory spriteFactory;

    /* NORTH AND SOUTH HAVE TO BE FLIPPED */
    private static final String resourcePath = "src/resources/img/";

    private static final String LIGHT_GRASS_PATH = resourcePath + "tile/LightGrass.png";
    private static final String LIGHT_WATER_PATH = resourcePath + "tile/LightWater.png";
    private static final String LIGHT_MOUNTAIN_PATH = resourcePath + "tile/LightMountain.png";
    private static final String DARK_GRASS_PATH = resourcePath + "tile/DarkGrass.png";
    private static final String DARK_WATER_PATH = resourcePath + "tile/DarkWater.png";
    private static final String DARK_MOUNTAIN_PATH = resourcePath + "tile/DarkMountain.png";
    private static final String GUN_PATH = resourcePath + "item/gun.png";
    private static final String HEALTH_POTION_PATH = resourcePath + "item/RecoveryHeart.png";
    private static final String HALO_PATH = resourcePath + "item/Halo.png";
    private static final String HERMES_BOOTS_PATH = resourcePath + "item/HermesBoots.png";
    //private static final String JESUS_BOOTS = resourcePath + "item/gun.png";
    private static final String RUSTY_KNIFE_PATH = resourcePath + "item/RustyKnife.png";
    private static final String MACE_PATH = resourcePath + "item/Mace.png";
    private static final String STICK_PATH = resourcePath + "item/Stick.png";
    private static final String HORNES_PATH = resourcePath + "item/Hornes.png";
    private static final String RECOVERY_HEART_PATH = resourcePath + "item/RecoveryHeart.png";
    private static final String DOOR_OPENER_PATH = resourcePath + "item/DoorOpener.png";
    private static final String DOOR_PATH = resourcePath + "item/Door.png";

    /*ENTITY*/
    private static final String SLIME_PATH = resourcePath + "entity/slime.png";
    private final BufferedImage SLIME;
    private static final String POP_EYE_PATH = resourcePath + "entity/popeye.gif";
    private BufferedImage POP_EYE;
    
    private static final String ANGEL_PATH = resourcePath + "entity/angel.gif";
    private BufferedImage ANGEL;

    /*TRAP*/
    private static final String BEAR_TRAP_PATH = resourcePath + "trap/BearTrap.png";
    private final BufferedImage BEAR_TRAP;

    /*AREA EFFECT*/
    private final String HEAL_PATH = resourcePath + "area_effect/heal.jpg";
    private final String INSTANT_DEATH_PATH = resourcePath + "area_effect/instant_death.png";
    private final String INSTANT_DEATH2_PATH = resourcePath + "area_effect/instant_death2.png";
    private final String LEVEL_UP_PATH = resourcePath + "area_effect/level_up.png";
    private final String TAKE_DAMAGE_PATH = resourcePath + "area_effect/take_damage.png";
    private final String TELEPORT_PATH = resourcePath + "area_effect/teleport.jpeg";

    private final BufferedImage HEAL;
    private final BufferedImage INSTANT_DEATH;
    private final BufferedImage INSTANT_DEATH2;
    private final BufferedImage LEVEL_UP;
    private final BufferedImage TAKE_DAMAGE;
    private final BufferedImage TELEPORT;

    private final BufferedImage LIGHT_GRASS;
    private final BufferedImage LIGHT_WATER;
    private final BufferedImage LIGHT_MOUNTAIN;
    private final BufferedImage DARK_GRASS;
    private final BufferedImage DARK_WATER;
    private final BufferedImage DARK_MOUNTAIN;
    private final BufferedImage GUN;
    private final BufferedImage HEALTH_POTION;
    private final BufferedImage HALO;
    private final BufferedImage HERMES_BOOTS;
    //private static final String JESUS_BOOTS = resourcePath + "item/gun.png";
    private final BufferedImage RUSTY_KNIFE;
    private final BufferedImage MACE;
    private final BufferedImage STICK;
    private final BufferedImage HORNES;
    private final BufferedImage RECOVERY_HEART;
    private final BufferedImage DOOR_OPENER;
    private final BufferedImage DOOR;
    private final BufferedImage FOG;
    private final BufferedImage FIREBALL;
    private final BufferedImage GENERIC;

    private SpriteFactory() {
        LIGHT_GRASS = getImage(LIGHT_GRASS_PATH);
        LIGHT_WATER = getImage(LIGHT_WATER_PATH);
        LIGHT_MOUNTAIN = getImage(LIGHT_MOUNTAIN_PATH);
        DARK_GRASS = getImage(DARK_GRASS_PATH);
        DARK_WATER = getImage(DARK_WATER_PATH);
        DARK_MOUNTAIN = getImage(DARK_MOUNTAIN_PATH);
        GUN = getImage(GUN_PATH);
        HEALTH_POTION = getImage(HEALTH_POTION_PATH);
        HALO = getImage(HALO_PATH);
        HERMES_BOOTS = getImage(HERMES_BOOTS_PATH);
        RUSTY_KNIFE = getImage(RUSTY_KNIFE_PATH);
        MACE = getImage(MACE_PATH);
        STICK = getImage(STICK_PATH);
        HORNES = getImage(HORNES_PATH);
        RECOVERY_HEART = getImage(RECOVERY_HEART_PATH);
        DOOR_OPENER = getImage(DOOR_OPENER_PATH);
        DOOR = getImage(DOOR_PATH);
        /*AREA EFFECT*/
        HEAL = getImage(HEAL_PATH);
        INSTANT_DEATH = getImage(INSTANT_DEATH_PATH);
        INSTANT_DEATH2 = getImage(INSTANT_DEATH2_PATH);
        LEVEL_UP = getImage(LEVEL_UP_PATH);
        TAKE_DAMAGE = getImage(TAKE_DAMAGE_PATH);
        TELEPORT = getImage(TELEPORT_PATH);
        /*TRAP*/
        BEAR_TRAP = getImage(BEAR_TRAP_PATH);
        /*ENTITY*/
        SLIME = getImage(SLIME_PATH);

        FOG = getImage(resourcePath + "tile/fog.png");
        FIREBALL = getImage(resourcePath + "projectile/fireball.png");
        GENERIC = getImage(resourcePath + "generic.png");

    }

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
        return getInstance().getGenericObj();
    }

    private BufferedImage getGenericObj() {
        return GENERIC;
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

    public static BufferedImage getFog() {
        return getInstance().getSmoke();

    }

    private BufferedImage getSmoke() {
        return FOG;
    }

    public static BufferedImage getFireball() {
        return getInstance().getFire();
    }

    private BufferedImage getFire() {
        return FIREBALL;
    }

    public static BufferedImage hashIDtoImage(String id) {
        return getInstance().hashIDtoImg(id);
    }

    private BufferedImage hashIDtoImg(String id) {
        switch (id) {
            case "1":
                return LIGHT_GRASS;
            case "2":
                return LIGHT_WATER;
            case "3":
                return LIGHT_MOUNTAIN;
            case "4":
                return DARK_GRASS;
            case "5":
                return DARK_WATER;
            case "6":
                return DARK_MOUNTAIN;
            case "gun":
                return GUN;

            case "healthPotion":
                return HEALTH_POTION;

            case "halo":
                return HALO;

            case "hermesBoots":
                return HERMES_BOOTS;

            case "jesusBoots":
            //return JESUS_BOOTS;

            case "rustyKnife":
                return RUSTY_KNIFE;

            case "mace":
                return MACE;

            case "stick":
                return STICK;

            case "hornes":
                return HORNES;

            case "sword":
            //return SWORD;

            case "recoveryHeart":
                return RECOVERY_HEART;

            case "doorOpener":
                return DOOR_OPENER;
            case "door":
                return DOOR;
            case "heal":
                return HEAL;
            case "instantDeath":
                if (Math.random() * 2 < 1) {
                    return INSTANT_DEATH;

                } else {
                    return INSTANT_DEATH2;

                }
            case "levelUp":
                return LEVEL_UP;
            case "teleport":
                return TELEPORT;
            case "dealDamageTrap":
                return BEAR_TRAP;
            case "evilMuscleSlime":
                return SLIME;
            case "friendlyOldBrawler":
                return POP_EYE;
            case "lightGuardian":
                return ANGEL;

            default:
                //System.out.println(id + " wheres the pic file?");
                return getGenericObject();

        }
    }

    public static BufferedImage getGenericEntity(Direction direction) {
        return getGenericObject();
    }

    /**
     *
     * @return
     */
    public static SpriteFactory getInstance() {
        if (spriteFactory == null) {
            spriteFactory = new SpriteFactory();
        }
        return spriteFactory;
    }
}

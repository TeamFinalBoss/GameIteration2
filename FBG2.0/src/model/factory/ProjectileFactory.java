package model.factory;

import model.map.Direction;
import model.gameObject.projectile.Projectile;

/**
 *
 * @author ChrisMoscoso
 */
public class ProjectileFactory {

    /**
     * Creates a new fireball at the specified location in the specified
     * direction.
     *
     * @param x the x location of the projectile
     * @param y the y location of the projectile
     * @param direction the direction of the projectile
     * @return a new fireball projectile.
     */
    public static Projectile newFireBall(int x, int y, Direction direction) {
        Projectile p = new Projectile(x, y, direction);
        switch (p.getDirection()) {
            case North:
                p.setSpritePath(SpriteFactory.FIREBALL_NORTH);
                break;
            case East:
                p.setSpritePath(SpriteFactory.FIREBALL_EAST);
                break;
            case South:
                p.setSpritePath(SpriteFactory.FIREBALL_SOUTH);
                break;
            case West:
                p.setSpritePath(SpriteFactory.FIREBALL_WEST);
                break;
        }

        return p;
    }

}

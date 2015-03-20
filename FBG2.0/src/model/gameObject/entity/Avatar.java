package model.gameObject.entity;

import model.director.CombatCoordinator;
import model.factory.ProjectileFactory;
import model.factory.SpriteFactory;
import view.viewport.StatusViewPort;

/**
 *
 * @author ChrisMoscoso
 */
public class Avatar extends Entity {

    private static Avatar avatar;

    public Avatar() {
        super();
        super.myStats.setMovement(2);
        avatar = this;
        this.setSpritePath(SpriteFactory.PLAYER_SOUTH);
    }

    /**
     *
     * @return
     */
    public static Avatar getAvatar() {
        return avatar;
    }

    public void shoot() {
        int manaCostToShoot = 20;
        int current = myStats.getMpCurrent();
        if (current >= manaCostToShoot) { 
            CombatCoordinator.spawnProjectile(ProjectileFactory.newFireBall(location.x, location.y, myDirection));
            myStats.setmpCurrent(current - manaCostToShoot);
        }

    }

    public void addObserverOfStats(StatusViewPort statusVP) {
        myStats.addObserver(statusVP);
    }

}

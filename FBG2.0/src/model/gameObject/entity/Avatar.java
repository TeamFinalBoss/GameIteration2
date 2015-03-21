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
        super.myStats.setSpeed(2);
        super.myStats.setOffense(4);
        super.myStats.setIntellect(10);
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
        int current = myStats.getCurrentMana();
        if (current >= manaCostToShoot) { 
            CombatCoordinator.spawnProjectile(ProjectileFactory.newFireBall(location.x + myDirection.dx, location.y + myDirection.dy, myDirection, myStats));
            myStats.setCurrentMana(current - manaCostToShoot);
        }

    }

    public void addObserverOfStats(StatusViewPort statusVP) {
        myStats.addObserver(statusVP);
    }

}

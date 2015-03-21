package model.gameObject.entity;

import java.util.Observer;
import model.director.CombatCoordinator;
import model.factory.ProjectileFactory;
import model.factory.SpriteFactory;
import model.gameObject.entity.inventory.Sack;
import view.viewport.ArmoryViewPort;
import view.viewport.SackViewPort;
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

    /**
     * Gets the sack.
     * @return the sack of the avatar's inventory.
     */
    public Sack getSack(){
        return myInventory.getMySack();
    }
    
    /**
     * Adds an observer to the stats of the avatar.
     * @param o the observer to add.
     */
    public void addObserverOfStats(Observer o) {
        myStats.addObserver(o);
    }

    /**
     * Adds an observer to the armory of the avatar.
     * @param o the observer to add.
     */
    public void addObserverOfArmory(Observer o) {

        myInventory.getMyArmory().addObserver(o);
    }

    /**
     * Adds an observer to the sack of the avatar.
     * @param o the observer to add.
     */
    public void addObserverOfSack(Observer o) {
        myInventory.getMySack().addObserver(o);
    }

    public void toggleSack() {
        Boolean alreadyVisible = myInventory.getMySack().isVisible();
        myInventory.getMySack().setIsVisible(!alreadyVisible);
    }

    public void toggleArmory() {
        Boolean alreadyVisible = myInventory.getMyArmory().isVisible();
        myInventory.getMyArmory().setIsVisible(!alreadyVisible);
    }

}

package model.gameObject.entity;

import java.util.Observer;
import model.director.CombatCoordinator;
import model.factory.ProjectileFactory;
import model.factory.SpriteFactory;
import model.gameObject.entity.inventory.Sack;
import model.util.gameTimer.GameTimer;
import model.util.gameTimer.GameTimerListener;

/**
 *
 * @author ChrisMoscoso
 */
public class Avatar extends Entity {

    private static Avatar avatar;
    private static GameTimer ability1Cooldown;
    private static boolean canShoot = true;

    public Avatar() {
        super();
        super.myStats.setSpeed(3);
        super.myStats.setOffense(4);
        super.myStats.setIntellect(10);
        super.myStats.setManaRegenPerSecond(10);
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
        if (canShoot) {
            int manaCostToShoot = 20;
            int current = myStats.getCurrentMana();
            if (current >= manaCostToShoot) {
                CombatCoordinator.spawnProjectile(ProjectileFactory.newFireBall(location.x + myDirection.dx, location.y + myDirection.dy, myDirection, myStats));
                myStats.setCurrentMana(current - manaCostToShoot);
            }
            canShoot = false;
            ability1Cooldown = new GameTimer(15);
            ability1Cooldown.setGameTimerListener(new GameTimerListener() {

                @Override
                public void trigger() {
                    canShoot = true;
                }

            });
            ability1Cooldown.start();
        }
    }

    /**
     * Gets the sack.
     *
     * @return the sack of the avatar's inventory.
     */
    public Sack getSack() {
        return myInventory.getMySack();
    }

    /**
     * Adds an observer to the stats of the avatar.
     *
     * @param o the observer to add.
     */
    public void addObserverOfStats(Observer o) {
        myStats.addObserver(o);
    }

    /**
     * Adds an observer to the armory of the avatar.
     *
     * @param o the observer to add.
     */
    public void addObserverOfArmory(Observer o) {

        myInventory.getMyArmory().addObserver(o);
    }

    /**
     * Adds an observer to the sack of the avatar.
     *
     * @param o the observer to add.
     */
    public void addObserverOfSack(Observer o) {
        myInventory.getMySack().addObserver(o);
    }

    /**
     * Toggles the visibility of the sack POSTCONDITION: isVisible = !isVisible
     */
    public void toggleSack() {
        Boolean alreadyVisible = myInventory.getMySack().isVisible();
        myInventory.getMySack().setIsVisible(!alreadyVisible);
    }

    /**
     * Toggles the visibility of the armory. POSTCONDITION: isVisible =
     * !isVisible
     */
    public void toggleArmory() {
        Boolean alreadyVisible = myInventory.getMyArmory().isVisible();
        myInventory.getMyArmory().setIsVisible(!alreadyVisible);
    }
    
}


package model.director;

import model.gameObject.projectile.Projectile;


/**
 *
 * @author ChrisMoscoso
 */
public class CombatCoordinator {

    public static void spawnProjectile(Projectile p){
        GameDirector.getActiveMap().addProjectile(p);
    }
    
}

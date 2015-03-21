
package model.director;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import model.gameObject.entity.Entity;
import model.gameObject.projectile.Projectile;
import model.stats.PlayerStats;


/**
 *
 * @author ChrisMoscoso
 */
public class CombatCoordinator {

    public static void spawnProjectile(Projectile p){
        GameDirector.getActiveMap().addProjectile(p);
    }
    
    public static void checkCollideProjectiles(ArrayList<Projectile> pList, ArrayList<Entity> eList){
        try{
            for(Projectile p : pList){
                for(Entity e : eList){
                    if(p.getLocation().equals(e.getLocation())){
                        applySummonerDamage(p.getPlayerStats(), e.getPlayerStats());
                        pList.remove(p);
                    }
                }
            }
        }catch(ConcurrentModificationException e){}
    }
    
    public static void applySummonerDamage(PlayerStats offense, PlayerStats defense){
        int damageGiven = offense.getOffense() * offense.getIntellect();
        int bonusDamage = (int) (5 * Math.random());
        
        int damageReceived = damageGiven + bonusDamage - defense.getDefense();
        
        defense.modCurrentHealth(-damageReceived);
    }
    
}

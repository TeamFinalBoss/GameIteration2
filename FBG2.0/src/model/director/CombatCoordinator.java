
package model.director;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import model.map.GameMap;
import model.map.pair.CoordinatePair;
import model.effect.Effect;
import model.entity.Entity;
import model.entity.stats.Stats;
import model.map.Projectile;

/**
 *
 * @author ChrisMoscoso
 */
public class CombatCoordinator {
    ActiveMapManager AMM;
    private static CombatCoordinator me;
    
    public CombatCoordinator(){
        AMM = ActiveMapManager.getInstance();
    }
    
    public static CombatCoordinator getInstance(){
        if(me == null){
            me = new CombatCoordinator();
        }
        return me;
    }
    
      
      
    public void attemptAffectEntities(ArrayList<CoordinatePair> affectedTiles, Effect myEffect) {
        for(CoordinatePair CP: affectedTiles){
            Entity ent = AMM.getEntityAtCoordinate(CP);
            if(ent!=null)
                myEffect.applyEffect(ent);
        }
        
    }
    
    public static void spawnProjectile(Projectile p){
        //GameDirector.getActiveMap().addProjectile(p);
    }
    
    public static void checkCollideProjectiles(ArrayList<Projectile> pList, ArrayList<Entity> eList){
        try{
            for(Projectile p : pList){
                for(Entity e : eList){
                    if(p.getLocation().equals(e.getLocation())){
                        //applySummonerDamage(p.getPlayerStats(), e.getPlayerStats());
                        pList.remove(p);
                    }
                }
            }
        }catch(ConcurrentModificationException e){}
    }
    
    public static void applySummonerDamage(Stats offense, Stats defense){
        int damageGiven = offense.offense()* offense.intellect();
        int bonusDamage = (int) (5 * Math.random());
        
        int damageReceived = damageGiven + bonusDamage - defense.defense();
        
        defense.modifyCurrentHP(-damageReceived);
    }
    
}

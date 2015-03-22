
package model.director;

import java.util.ArrayList;

import model.map.GameMap;
import model.map.pair.CoordinatePair;
import model.effect.Effect;
import model.entity.Entity;

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
    
}

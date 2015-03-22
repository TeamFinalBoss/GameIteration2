
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

    public static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    GameMap myMap;
    
    public CombatCoordinator(){
        myMap = ActiveMapManager.getInstance().getActiveMap();
    }
    
    /**
     * Changes which map the CC is using. Alternatively, could update using
     * ActiveMapManager.getInstance()
     * @author Jason Owens
     * @param map 
     */
    public void setCurrentGameMap(GameMap map){
        myMap = map;
    }
    
    public void attemptAffectEntities(ArrayList<CoordinatePair> affectedTiles, Effect myEffect) {
        for(CoordinatePair CP: affectedTiles){
            Entity ent = myMap.getEntityAtCoordinate(CP);
            if(ent!=null)
                myEffect.applyEffect(ent);
        }
    }
    
}

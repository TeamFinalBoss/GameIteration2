
package model.director;

import java.util.ArrayList;

import model.effects.Effect;
import model.map.GameMap;
import model.map.pair.CoordinatePair;

/**
 *
 * @author ChrisMoscoso
 */
public class CombatCoordinator {
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
            Effect temp = new Effect(myMap.getEntityAtCoordinate(CP));
        }
    }
    
}

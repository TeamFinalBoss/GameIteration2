package model.director;

import model.entity.Entity;
import java.util.ArrayList;
import model.map.GameMap;

/**
 * Maintains a list of all the different game maps and keeps track of the
 * current active map. It provides map info to external systems.
 *
 * @author Hanif, ChrisMoscoso
 */
public class ActiveMapManager {
    private ArrayList<GameMap> maps;
    private GameMap activeMap;
    private Entity avatar; 
    
    public void requestMovement(Entity e){
        
    }
}

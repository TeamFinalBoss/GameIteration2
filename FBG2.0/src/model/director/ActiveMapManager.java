package model.director;

import model.entity.Entity;
import java.util.ArrayList;
import model.map.Direction;
import model.map.GameMap;

/**
 * Maintains a list of all the different game maps and keeps track of the
 * current active map. It provides map info to external systems.
 *
 * Made it a singleton. Let me know if that's terrible. (Jason)
 * 
 * @author Jason Owens, Hanif, ChrisMoscoso
 */
public class ActiveMapManager {
    private static ActiveMapManager me;
    
    
    /*
    Indices for different GameMaps in maps
        0: First Game Map (possible character creation?)
        1: ...
        2: ...    
    */
    
    private ArrayList<GameMap> maps;
    private GameMap activeMap;
    private Entity avatar; 
    
    /*----------Constructors------------*/
    public ActiveMapManager(){
        maps = new ArrayList<>();
    }
    
    public static ActiveMapManager getInstance(){
        if(me == null){
            me = new ActiveMapManager();
        }
        return me;
    }
    
    /**
     * 
     * @author Jason Owens
     * @return GameMap the active map
     */
    public GameMap getActiveMap(){
        return activeMap;
    }
    
    
    /*--------------Mutators----------------*/
    
    public boolean requestMovement(Entity e, Direction d){
        return activeMap.requestMovement(e, d);
    }
}

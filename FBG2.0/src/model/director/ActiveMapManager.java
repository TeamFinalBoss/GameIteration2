
package model.director;

import model.entity.SummonerAvatar;
import model.entity.Entity;
import model.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import model.map.Direction;
import model.map.GameMap;
import model.map.MapSwitcher;
import model.map.Projectile;
import model.map.pair.CoordinatePair;
import model.map.areaEffect.AreaEffect;
import model.map.tile.Tile;
import model.map.tile.trap.Trap;

//TODO: add following function: public void getEverythingInRange(CoordinatePair center, int radius, 
//      List<Tile> containedTiles, List<Projectile> containedProjectiles, List<Entity> containedEntities, 
//		List<Trap> containedTraps, List<Item> containedItems, List<AreaEffect> containedAreaEffects)

/**
 * TODO make sure Entity supports useAbility(int)
 * 
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
       // avatar = Avatar.getPlayer();
    }
    
    public ActiveMapManager(Entity avatar){
    	maps = new ArrayList<>();
    	this.avatar = avatar;
    }
    
    
    public static ActiveMapManager getInstance(){
        if(me == null){
            me = new ActiveMapManager();
            me.avatar = new SummonerAvatar();
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
        
    public void getEverythingInRange(CoordinatePair center, int radius, 
      List<Tile> containedTiles, List<Projectile> containedProjectiles, List<Entity> containedEntities, 
		List<Trap> containedTraps, List<Item> containedItems, List<AreaEffect> containedAreaEffects){
        
       /* activeMap.getEverythingInRange( center,  radius, 
        
        activeMap.getEverythingInRange( center,  radius, 
      containedTiles, containedProjectiles, containedEntities, 
		 containedTraps,  containedItems, containedAreaEffects);*/
        
    }
    
    /**
     * Adds a map to the list of possible maps
     * 
     * @author Michael Cohen
     * @param map to be added
     */
    public void addMap(GameMap map){
    	maps.add(map);
    }   
    /**
     * Removes a map from the list of possible maps.
     * Returns true if map was present and removed
     * Returns false if map was not present
     * 
     * @author Michael Cohen
     * @param map to be removed
     * @return true if map was present and removed. Else return false
     */
    public boolean removeMap(GameMap map){
    	for (GameMap m : this.maps){
    		if (map == m){
    			this.maps.remove(m);
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Clears all maps.
     * 
     * @author Aidan Pace
     */
    public void clearMaps() {
    	this.maps.clear();
    	return;
    }
    
    /**
     * If map is in maps, set it as active map and return true. Else return false
     * 
     * TODO: Determine if method signature is needed
     * 
     * @author Michael Cohen
     * @param map to be set as active map
     * @return true if map was in maps, else false
     */
    public boolean setActiveMap(GameMap map){
    	if (this.maps.contains(map)){
    		this.activeMap = map;
    		
    		return true;
    	}
    	return false;
    }
    
    /**\
     * Set the active map to the map with the given mapID int
     * 
     * @author Aidan Pace
     * @param mapID the requested mapID
     * @return true if map was in maps, else false
     */
    public boolean setActiveMap(int mapID) {
    	for(GameMap m : this.maps) {
    		if(m.getID == mapID) {
    			this.activeMap = m;
    			return true;
    		}
    	}
    	return false;
    }
    
    /*---------------- Active Map Interaction ------------------- */
    
    /**
     * Attempts to add an entity to the active map at the given CP
     * 
     * @author Michael Cohen
     * @param e entity to be added
     * @param CP coordinate pair to set as the entity's location
     */
    public void addEntityToActiveMap(Entity e, CoordinatePair CP){
    	this.activeMap.addEntity(e, CP);
    }
    
    /**
     * Attempts to remove an entity from the active map
     * 
     * @author Michael Cohen
     * @param e entity to be removed
     */
    public void removeEntityFromActiveMap(Entity e){
    	this.activeMap.removeEntity(e);
    }
    
    /**
     * Attempts to remove an entity from the active map at the
     * given CoordinatePair. Returns entity if it was present at the 
     * coordinate, else throws a runtime exception
     * 
     * @author Michael Cohen
     * @param CP of expected entity
     * @return Entity that was removed if it was present
     */
    public Entity removeEntityFromActiveMapAt(CoordinatePair CP){
    	return this.activeMap.removeEntity(CP);
    }
    
    /**
     * Attempts to add an item to the active map at the given CP
     * 
     * @author Michael Cohen
     * @param i item to be added
     * @param CP coordinate pair to set as the item's location
     */
    public void addItemToActiveMap(Item i, CoordinatePair CP){
    	this.activeMap.addItem(i, CP);
    }
    
    /**
     * Attempts to remove an item from the active map
     * 
     * @author Michael Cohen
     * @param item to be removed
     */
    public void removeItemFromActiveMap(Item item){
    	this.activeMap.removeItem(item);
    }
    
    /**
     * Attempts to remove an item from the active map at the
     * given CoordinatePair. Returns item if it was present at the 
     * coordinate, else throws a runtime exception
     * 
     * @author Michael Cohen
     * @param CP of expected item
     * @return item that was removed if it was present
     */
    public Item removeItemFromActiveMapAt(CoordinatePair CP){
    	return this.activeMap.removeItem(CP);
    }
    
    /**
     * Attempts to add an area effect to the active map at the given CP
     * 
     * @author Michael Cohen
     * @param a area effect to be added
     * @param CP coordinate pair to set as the area effect's location
     */
    public void addAreaEffectToActiveMap(AreaEffect a, CoordinatePair CP){
    	this.activeMap.addAreaEffect(a, CP);
    }
    
    /**
     * Attempts to remove an area effect from the active map
     * 
     * @author Michael Cohen
     * @param effect to be removed
     */
    public void removeAreaEffectFromActiveMap(AreaEffect effect){
    	this.activeMap.removeAreaEffect(effect);
    }
    
    /**
     * Attempts to remove an area effect from the active map at the
     * given CoordinatePair. Returns area effect if it was present at the 
     * coordinate, else throws a runtime exception
     * 
     * @author Michael Cohen
     * @param CP of expected area effect
     * @return AreaEffect that was removed if it was present
     */
    public AreaEffect removeAreaEffectFromActiveMapAt(CoordinatePair CP){
    	return this.activeMap.removeAreaEffect(CP);
    }
    
    /**
     * Attempts to add a switcher to the active map at the given CP
     * 
     * @author Michael Cohen
     * @param switcher to be added
     * @param CP coordinate pair to set as the switcher's location
     */
    public void addMapSwitcherToActiveMap(MapSwitcher switcher, CoordinatePair CP){
    	this.activeMap.addMapSwitcher(switcher, CP);
    }
    
    /**
     * Attempts to remove a MapSwitcher from the active map
     * 
     * @author Michael Cohen
     * @param switcher to be removed
     */
    public void removeMapSwitcherFromActiveMap(MapSwitcher switcher){
    	this.activeMap.removeMapSwitcher(switcher);
    }
    
    
    /**
     * Attempts to remove a MapSwitcher from the active map at the
     * given CoordinatePair. Returns MapSwitcher if it was present at the
     * coordinate, else throws a runtime exception
     * 
     * @author Michael Cohen
     * @param CP of expected MapSwitcher
     * @return MapSwitcher that was removed if it was present
     */
    public MapSwitcher removeMapSwitcherFromActiveMap(CoordinatePair CP){
    	return this.activeMap.removeMapSwitcher(CP);
    }
    
    /**
     * Attempts to add a trap to the active map at the given CP
     * 
     * @author Michael Cohen
     * @param t trap to be added
     * @param CP coordinate pair to set as the trap's location
     */
    public void addTrapToActiveMap(Trap t, CoordinatePair CP){
    	this.activeMap.addTrap(t, CP);
    }
    
    /**
     * Attempts to remove a Trap from the active map
     * 
     * @author Michael Cohen
     * @param Trap to be removed
     */
    public void removeTrapFromActiveMap(Trap trap){
    	this.activeMap.removeTrap(trap);
    }
    
    /**
     * Attempts to remove a Trap from the active map at the
     * given CoordinatePair. Returns Trap if it was present at the 
     * coordinate, else throws a runtime exception
     * 
     * @author Michael Cohen
     * @param CP of expected Trap
     * @return Trap that was removed if it was present
     */
    public Trap removeTrapFromActiveMapAt(CoordinatePair CP){
    	return this.activeMap.removeTrap(CP);
    }

    /**
     * Adds an observer to the active map
     * 
     * @author Michael Cohen
     * @param o observer to be added
     */
    public void addObserverToActiveMap(Observer o){
    	this.activeMap.addObserver(o);
    }
    
    	
    /*--------------Mutators----------------*/
    
    public boolean requestMovement(Entity e, Direction d){
        return activeMap.requestMovement(e, d);
    }
    public boolean moveAvatar(Direction d){
        return requestMovement(avatar, d);
    }
    public boolean useAbility(Entity e, int abilityToUse){
        return activeMap.useAbility( e, abilityToUse);
    }
    public boolean useAvatarAbility(int abilityToUse){
        return useAbility(avatar, abilityToUse);
    }

    Entity getEntityAtCoordinate(CoordinatePair CP) {
        return activeMap.getEntityAtCoordinate(CP);
    }
}

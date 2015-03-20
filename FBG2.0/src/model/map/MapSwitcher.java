package model.map;

import model.director.ActiveMapManager;
import model.entity.Entity;
import model.gameObject.MapObject;
import model.map.pair.CoordinatePair;

/**
 * This relationship class allows an entity to travel between two GameMaps
 * Extends MapObject
 * 
 * ID: 11
 * 
 * @see MapObject
 * @see GameMap
 * @see Entity
 * @author Michael Cohen
 *
 */
public class MapSwitcher extends MapObject {
	
	CoordinatePair location1, location2;
	GameMap map1, map2;
	ActiveMapManager manager;
	boolean currentMapToggle; //false == map1; true == map2
	
	public MapSwitcher(){
		super("Generic MapSwitcher", "Generic description", new CoordinatePair());
		
		location1 = new CoordinatePair();
		location2 = new CoordinatePair();
		
		map1 = new GameMap();
		map2 = new GameMap();
		
		this.id = "11";
		this.className = "MapSwitcher";
		
		manager = ActiveMapManager.getInstance();
		currentMapToggle = false;
	}
	
	public MapSwitcher(String objectName, String description, GameMap m1, CoordinatePair l1, GameMap m2, CoordinatePair l2){
		super(objectName, description, l1);
		
		this.id = "11";
		this.className = "MapSwitcher";
		
		location1 = l1;
		location2 = l2;
		
		map1 = m1;
		map2 = m2;
		
		manager = ActiveMapManager.getInstance();
		currentMapToggle = false;
	}
	
	/**
	 * Sets location1
	 * 
	 * @author Michael Cohen
	 * @param CP the CoordinatePair to be set
	 */
	public void setLocation1(CoordinatePair CP){
		this.location1 = CP;
	}
	
	/**
	 * Sets location2
	 * 
	 * @author Michael Cohen
	 * @param CP the CoordinatePair to be set
	 */
	public void setLocation2(CoordinatePair CP){
		this.location2 = CP;
	}
	
	/**
	 * Sets map1
	 * 
	 * @author Michael Cohen
	 * @param m the GameMap to be set
	 */
	public void setMap1(GameMap m){
		this.map1 = m;
	}
	
	/**
	 * Sets map2
	 * 
	 * @author Michael Cohen
	 * @param m the GameMap to be set
	 */
	public void setMap2(GameMap m){
		this.map2 = m;
	}
	
	/**
	 * This method removes Entity e from the current active map,
	 * sets the new active map as the secondary map,
	 * sets the new location as the secondary location,
	 * places Entity e in the new map at the new location,
	 * and switches the primary and secondary maps and locations
	 * 
	 * @param e entity to move between maps
	 */
	public void activate(Entity e){
		
		manager.removeEntityFromActiveMap(e);
		
		if (!currentMapToggle){ //map1 is active -> switch to map2
			this.setLocation(location2);			
			manager.setActiveMap(map2);
		}
		else { //map2 is active -> switch to map1
			this.setLocation(location1);
			manager.setActiveMap(map1);
		}
		
		e.setLocation(this.getLocation());
		manager.addEntityToActiveMap(e, e.getLocation());
		currentMapToggle = !currentMapToggle;
	}
	
}

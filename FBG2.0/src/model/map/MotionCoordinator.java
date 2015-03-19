package model.map;

import model.entity.Entity;
import model.item.Item;
import model.map.pair.CoordinatePair;
import model.map.tile.AreaEffect;
import model.map.tile.Trap;

/**
 * This singleton class executes movement commands for entities and activates any 
 * effects or consequences that come with moving onto a tile, such as picking
 * up an item.
 * 
 * @see ActiveMapManager
 * @author Michael Cohen
 *
 */
public class MotionCoordinator {
	private static MotionCoordinator me = null;
	
	private MotionCoordinator(){
		
	}
	
	public static MotionCoordinator getInstance(){
		if (me == null){
			me = new MotionCoordinator();
		}
		
		return me;
	}
	
	public void moveEntity(Entity e, CoordinatePair desiredLocation, AreaEffect effect, Item i, Trap t){
		e.setLocation(desiredLocation);
		
		if (effect != null){
			effect.activate(e);
		}
		
		if (i != null){
			i.useOnMap(e); //TODO: Create this method for every item type
		}
		
		if(t != null){
			t.activate(e); //TODO: Create this method for every item type
		}
	}
}

package model.map;

import model.entity.Entity;
import model.item.Item;
import model.map.pair.CoordinatePair;
import model.map.areaEffect.AreaEffect;
import model.map.tile.trap.Trap;

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
	
	
	public void moveEntity(Entity e, CoordinatePair desiredLocation, AreaEffect effect, Item i, MapSwitcher switcher, Trap t){
		CoordinatePair change = new CoordinatePair(desiredLocation.getX()-e.getLocation().getX(), desiredLocation.getY()-e.getLocation().getY());
		if(!e.modifyLocation(change)) return;
		
		if (effect != null){
                    effect.activate(e);
		}
		
		if (i != null){
                    i.activate(e); //TODO: Create this method for every item type
		}
		
		if(t != null){
                    t.Activate(e); //TODO: Create this method for every item type
		}
		
		if(switcher != null){
                    switcher.activate(e);
		}
	}
}

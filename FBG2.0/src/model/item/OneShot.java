package model.item;

import model.director.ActiveMapManager;
import model.entity.Entity;
import model.gameObject.MapObject;
import model.map.pair.CoordinatePair;

/**
 * This class defines an Item which is activated once stepped on in-game
 * Extends Item abstract class
 * 
 * ID: 6
 * 
 * @see MapObject
 * @see Item
 * @author Michael Cohen
 *
 */
public class OneShot extends Item {
	public OneShot(){
		super("Generic One Shot", "Generic description", new CoordinatePair());
		
		this.setID("6");
		this.setClassName("One Shot");
		
		//Other properties set here
	}
	
	public OneShot(String objectName, String description, CoordinatePair location){
		super(objectName, description, location);
		
		this.setID("6");
		this.setClassName("One Shot");
		
		//Other properties set here
	}
         @Override
        public String getType(){
            return "One Shot";
        }
        public boolean activate(Entity e){
        	e.modifyCurrentHP(10);
        	ActiveMapManager.getInstance().removeItemFromActiveMap(this);
        	return true;
        }
	
}

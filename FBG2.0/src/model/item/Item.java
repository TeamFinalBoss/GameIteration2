package model.item;

import model.gameObject.MapObject;
import model.map.pair.CoordinatePair;
import model.entity.Entity; 

/**
 * This abstract class defines a basic Item object which will be 
 * displayed on the map
 * Extends MapObject
 * 
 * ID: 2
 * 
 * @see MapObject
 * @author ashishag, Michael Cohen
 *
 */
public abstract class Item extends MapObject {
    
    int observationSkill;
	public Item(){
		super("Generic Item", "Generic description", new CoordinatePair());
		
		this.setID("2");
		this.setClassName("Item");
	
		//Other properties set here
	}
	
	public Item(String objectName, String description, CoordinatePair location){
		super(objectName, description, location);
		
		this.setID("2");
		this.setClassName("Item");
		
		//Other properties set here
	}
        
        // This is called when the entity activates the item
        // returns if the entity can continue
        // by default entity can continue
        
        public boolean activate(Entity e){
            return true;
        }
	
        public boolean canSee(int observationSkill){
            return true; 
        }
     
        
	
	
	
}

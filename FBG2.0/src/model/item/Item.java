package model.item;

import model.gameObject.MapObject;
import model.map.pair.CoordinatePair;

/**
 * This abstract class defines a basic Item object which will be 
 * displayed on the map
 * Extends MapObject
 * 
 * ID: 2
 * 
 * @see MapObject
 * @author Michael Cohen
 *
 */
public abstract class Item extends MapObject {
	public Item(){
		super("Generic Item", "Generic description", new CoordinatePair());
		
		this.id = "2";
		this.className = "Item";
	
		//Other properties set here
	}
	
	public Item(String objectName, String description, CoordinatePair location){
		super(objectName, description, location);
		
		this.id = "2";
		this.className = "Item";
		
		//Other properties set here
	}	
        
        /**
         * @author Jason Owens
         * @return whether or not the item impedes movement 
         */
	public abstract boolean isTraversable();
}

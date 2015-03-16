package model.item;

import model.gameObject.MapObject;
import model.map.pair.CoordinatePair;

/**
 * This class defines an Item which can be taken and added to the Inventory
 * Extends Item abstract class
 * 
 * ID: 3
 * 
 * @see MapObject
 * @see Item
 * @author Michael Cohen
 *
 */
public class Takeable extends Item {
	
	public Takeable(){
		super("Generic Takeable", "Generic description", new CoordinatePair());
		
		this.id = "3";
		this.className = "Takeable";
		
		//Other properties set here
	}
	
	public Takeable(String objectName, String description, CoordinatePair location){
		super(objectName, description, location);
		
		this.id = "3";
		this.className = "Takeable";
		
		//Other properties set here
	}
	
	public void useInSack(){
	}
}

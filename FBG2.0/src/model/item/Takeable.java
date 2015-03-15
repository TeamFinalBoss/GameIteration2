package model.item;

import model.gameObject.GameObject;
import model.map.pair.CoordinatePair;

/**
 * This class defines an Item which can be taken and added to the Inventory
 * Implements Item interface
 * Extends GameObject abstract class
 * 
 * ID: 2
 * 
 * @see GameObject
 * @author Michael Cohen
 *
 */
public class Takeable extends GameObject implements Item {
	
	public Takeable(){
		super("Generic Takeable", "Generic description", new CoordinatePair());
		
		this.id = "2";
		this.className = "Takeable";
		
		//Other properties set here
	}
	
	public Takeable(String objectName, String description, CoordinatePair location){
		super(objectName, description, location);
		
		this.id = "2";
		this.className = "Takeable";
		
		//Other properties set here
	}
	
	public void useInSack(){
	}
}

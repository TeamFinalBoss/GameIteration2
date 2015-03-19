package model.item;

import model.gameObject.MapObject;
import model.map.pair.CoordinatePair;
import model.entity.Entity;

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
	
	private int durability;
	private int value;
	
	public Takeable(){
		super("Generic Takeable", "Generic description", new CoordinatePair());
		
		this.id = "3";
		this.className = "Takeable";
		
		this.durability = 1;
		
		//Other properties set here
	}
	
	//created one more constructor for if the item is in an inventory to begin with
	public Takeable(String objectName, String description, int value, int durability){
		super(objectName, description, new CoordinatePair());
		
		this.id = "3";
		this.className = "Takeable";
		
		this.durability = durability;
		
		//Other properties set here
	}
	
	public Takeable(String objectName, String description, CoordinatePair location, int value, int durability){
		super(objectName, description, location);
		
		this.id = "3";
		this.className = "Takeable";
		
		this.durability = durability;
		
		//Other properties set here
	}
	
	public boolean useInSack(Entity target){
		//TODO: return true iff the effect goes through (example failure case: item has a prerequisite which target does not meet)
		return true;
	}
}

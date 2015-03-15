package model.item;

import model.gameObject.MapObject;
import model.map.pair.CoordinatePair;

/**
 * This class defines an Item which can be interacted with in-game
 * Extends Item abstract class
 * 
 * ID: 4
 * 
 * @see MapObject
 * @see Item
 * @author Michael Cohen
 *
 */
public class Interactive extends Item {
	
	public Interactive(){
		super("Generic Interactive", "Generic description", new CoordinatePair());
		
		this.id = "4";
		this.className = "Interactive";
		
		//Other properties set here
	}
	
	public Interactive(String objectName, String description, CoordinatePair location){
		super(objectName, description, location);
		
		this.id = "4";
		this.className = "Interactive";
		
		//Other properties set here
	}
}

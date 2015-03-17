package model.item;

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
		
		this.id = "6";
		this.className = "One Shot";
		
		//Other properties set here
	}
	
	public OneShot(String objectName, String description, CoordinatePair location){
		super(objectName, description, location);
		
		this.id = "6";
		this.className = "One Shot";
		
		//Other properties set here
	}
	
}
package model.item;

import model.gameObject.MapObject;
import model.map.pair.CoordinatePair;

/**
 * This class defines an Item which is activated once stepped on in-game
 * Implements Item interface
 * Extends GameObject abstract class
 * 
 * ID: 5
 * 
 * @see MapObject
 * @author Michael Cohen
 *
 */
public class OneShot extends MapObject implements Item {
	public OneShot(){
		super("Generic One Shot", "Generic description", new CoordinatePair());
		
		this.id = "5";
		this.className = "One Shot";
		
		//Other properties set here
	}
	
	public OneShot(String objectName, String description, CoordinatePair location){
		super(objectName, description, location);
		
		this.id = "5";
		this.className = "One Shot";
		
		//Other properties set here
	}
	
}

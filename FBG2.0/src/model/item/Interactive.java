package model.item;

import model.gameObject.GameObject;
import model.map.pair.CoordinatePair;

/**
 * This class defines an Item which can be interacted with in-game
 * Implements Item interface
 * Extends GameObject abstract class
 * 
 * ID: 3
 * 
 * @author Michael Cohen
 *
 */
public class Interactive extends GameObject implements Item {
	
	public Interactive(){
		super("Generic Interactive", "Generic description", new CoordinatePair());
		
		this.id = "3";
		this.className = "Interactive";
		
		//Other properties set here
	}
	
	public Interactive(String objectName, String description, CoordinatePair location){
		super(objectName, description, location);
		
		this.id = "3";
		this.className = "Interactive";
		
		//Other properties set here
	}
}

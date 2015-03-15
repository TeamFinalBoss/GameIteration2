package model.item;

import model.gameObject.MapObject;
import model.map.pair.CoordinatePair;

/**
 * This class defines an Item which serves as an obstacle in-game 
 * Extends Item abstract class
 * 
 * ID: 5
 * 
 * @see MapObject
 * @see Item
 * @author Michael Cohen
 *
 */
public class Obstacle extends Item {
	public Obstacle(){
		super("Generic Obstacle", "Generic description", new CoordinatePair());
		
		this.id = "5";
		this.className = "Obstacle";
		
		//Other properties set here
	}
	
	public Obstacle(String objectName, String description, CoordinatePair location){
		super(objectName, description, location);
		
		this.id = "5";
		this.className = "Obstacle";
		
		//Other properties set here
	}
}

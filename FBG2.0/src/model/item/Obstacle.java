package model.item;

import model.gameObject.GameObject;
import model.map.pair.CoordinatePair;

/**
 * This class defines an Item which serves as an obstacle in-game 
 * Implements Item interface
 * Extends GameObject abstract class
 * 
 * ID: 4
 * 
 * @author Michael Cohen
 *
 */
public class Obstacle extends GameObject implements Item {
	public Obstacle(){
		super("Generic Obstacle", "Generic description", new CoordinatePair());
		
		this.id = "3";
		this.className = "Obstacle";
		
		//Other properties set here
	}
	
	public Obstacle(String objectName, String description, CoordinatePair location){
		super(objectName, description, location);
		
		this.id = "3";
		this.className = "Obstacle";
		
		//Other properties set here
	}
}

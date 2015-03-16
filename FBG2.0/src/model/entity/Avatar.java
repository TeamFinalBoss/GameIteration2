package model.entity;

import model.map.Direction;
import model.map.pair.CoordinatePair;

/**
 * The class Avatar defines a singleton for the player-controlled
 * Entity in the game.
 * Extends Entity class
 * ID: 9
 * 
 * @see Entity
 * @author Michael Cohen
 *
 */
public class Avatar extends Entity {

	private static Avatar avatar = null;
	
	private Avatar(){
		super();
		
		this.className = "Avatar";
		this.name = "Generic Avatar";
		this.id = "9";
		
	}
	
	private Avatar(String objectName, String description, CoordinatePair location,
			Inventory inventory, Occupation occupation, Direction direction, int speed){
		super(objectName, description, location, inventory, occupation, direction, speed);
		
		this.className = "Avatar";
		this.name = "Generic Avatar";
		this.id = "9";
	}
	
	
	public static Avatar getPlayer(){
		if (avatar == null){
			avatar = new Avatar();
		}
		return avatar;
	}
	
	public static Avatar getPlayer(String name, String description, CoordinatePair location,
			Inventory inventory, Occupation occupation, Direction direction, int speed){
		if (avatar == null){
			avatar = new Avatar(name, description, location, inventory, occupation, direction, speed);
		}
		return avatar;
	}
}

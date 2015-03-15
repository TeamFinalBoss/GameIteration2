package model.map.tile;

import model.entity.Entity;
import model.gameObject.GameObject;
import model.map.pair.CoordinatePair;

/**
 * The purpose of this abstract class is to have an object that has a unique effect happen
 * when touched by an entity. In addition, it is possible for an entity to remove this
 * object from the game map.
 * Extends GameObject abstract class
 * 
 * ID: 7
 * 
 * @see GameObject
 * @author Aidan Pace, Michael Cohen
 */
public abstract class Trap extends GameObject{

	private int difficulty;
	
	Trap(){
		super("Generic Trap", "Generic description", new CoordinatePair());
		
		this.id = "7";
		this.className = "Trap";
		
		//Other properties set here
		this.difficulty = 0;
	}
	
	Trap(String objectName, String description, CoordinatePair location, 
			int difficulty){
		super(objectName, description, location);
		
		this.id = "7";
		this.className = "Trap";
		
		//Other properties set here
		this.difficulty = difficulty;
	}
	

	
	/**
	 * Determines if a trap was successfully disarmed.
	 * 
	 * @author Aidan Pace
	 * @param CallerLevel the trap disarming skill level of the entity attempting to disarm this trap
	 * @return whether or not the skill level was good enough
	 */
	public boolean attemptDisarm(int CallerLevel){
		if(CallerLevel >= difficulty) return true;
		return false;
	}
	
	/**
	 * Method to be overridden by subclasses to provide unique functionality
	 * when the trap is touched.
	 * 
	 * @author Aidan Pace
	 * @param caller The entity that activated the trap
	 * @see Entity
	 */
	abstract void Activate(Entity caller);

    void activate(Entity caller) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}